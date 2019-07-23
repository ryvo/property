import {Component, EventEmitter, Inject, Input, LOCALE_ID, OnDestroy, OnInit, Output} from '@angular/core';
import {Lease} from "../lease.model";
import {Observable} from "rxjs/internal/Observable";
import {LeasesService} from "../leases.service";
import {Tenant} from "../../tenants/tenant.model";
import {TenantService} from "../../tenants/tenant.service";
import {Subscription} from "rxjs/internal/Subscription";

@Component({
  selector: 'lease-editor',
  templateUrl: './lease-editor.component.html',
  styleUrls: ['./lease-editor.component.scss']
})
export class LeaseEditorComponent implements OnInit, OnDestroy {
  @Input() buildingUnitId;

  private _lease: any;
  selectedTenant: Tenant;

  private fetchTenantSubscription: Subscription;

  @Input('lease')
  set lease(newLease: Lease) {
    if (newLease) {
      this._lease = {
        id: newLease.id,
        startDate: newLease.startDate,
        endDate: newLease.endDate,
        _startDate: (newLease.startDate) ? new Date(newLease.startDate) : undefined,
        _endDate: (newLease.endDate) ? new Date(newLease.endDate) : undefined,
        monthlyRent: newLease.monthlyRent,
        tenantId: newLease.tenantId
      };

      if (newLease.tenantId) {
        this.fetchTenantSubscription = this.tenantsService.getTenant(newLease.tenantId)
          .subscribe((tenant: Tenant) => this.selectedTenant = tenant);
      }
    } else {
      this._lease = undefined;
      this.selectedTenant = undefined;
      if (this.fetchTenantSubscription) {
        this.fetchTenantSubscription.unsubscribe();
      }
    }
  }

  get lease(): Lease {
    if (this._lease) {
      let lease: Lease = new Lease();
      lease.id = this._lease.id;
      lease.startDate = (this._lease._startDate) ? this._lease._startDate.toISOString() : undefined;
      lease.endDate = (this._lease._endDate) ? this._lease._endDate.toISOString() : undefined;
      lease.monthlyRent = this._lease.monthlyRent;
      lease.tenantId = this._lease.tenantId;
      return lease;
    } else {
      return undefined;
    }
  }

  @Input() disabled: boolean = false;

  private _visible: boolean = false;

  @Input('visible')
  set visible(value: boolean) {
    this._visible = value && this.buildingUnitId && this._lease !== undefined;
  }

  @Output() visibleChange = new EventEmitter<boolean>();
  @Output() onCancel = new EventEmitter<void>();
  @Output() onSave = new EventEmitter<Lease>();

  private suggestedTenants: Tenant[];

  constructor(
    private leasesService: LeasesService,
    private tenantsService: TenantService,
    @Inject(LOCALE_ID) private locale: string) {
    console.log(this.locale);
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    if (this.fetchTenantSubscription) {
      this.fetchTenantSubscription.unsubscribe();
    }
  }

  isNew() {
    return typeof this._lease !== 'undefined' && typeof this._lease.id === 'undefined';
  }

  cancel() {
    this.hide();
    this.onCancel.emit();
  }

  submit() {
    let observable: Observable<Lease>;
    if (this.isNew()) {
      observable = this.leasesService.createLease(this.buildingUnitId, this.lease);
    } else {
      observable = this.leasesService.updateLease(this.lease);
    }

    observable.subscribe(
      (response: Lease) => {
        this.onSave.emit(response);
        this.hide()
      },
      (error) => {
      }
    )
  }

  hide() {
    this._visible = false;
    this.visibleChange.emit(this._visible);
  }

  searchTenants(event) {
    this.suggestedTenants = [];
    this.tenantsService.searchTenants(event.query).subscribe(
      (response: Tenant[]) => {
        this.suggestedTenants = response;
      }
    )
  }

  selectTenant(tenant: Tenant) {
    this._lease.tenantId = tenant.id;
  }

  unSelectTenant(tenant: Tenant) {
    this._lease.tenantId = undefined;
  }
}
