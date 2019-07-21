import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Lease} from "../lease.model";
import {BuildingUnit} from "../../building-units/building-unit.model";
import {Observable} from "rxjs/internal/Observable";
import {LeasesService} from "../leases.service";
import {Tenant} from "../../tenants/tenant.model";
import {TenantsService} from "../../tenants/tenants.service";

@Component({
  selector: 'lease-editor',
  templateUrl: './lease-editor.component.html',
  styleUrls: ['./lease-editor.component.scss']
})
export class LeaseEditorComponent implements OnInit {

  @Input() buildingUnitId;
  @Input() lease: Lease;
  @Input() disabled: boolean = false;

  private _visible: boolean = false;

  @Input('visible')
  set visible(value: boolean) {
    this._visible = value && this.buildingUnitId && this.lease !== undefined;
  }

  @Output() visibleChange = new EventEmitter<boolean>();
  @Output() onCancel = new EventEmitter<void>();
  @Output() onSave = new EventEmitter<Lease>();

  private suggestedTenants: Tenant[];

  constructor(
    private leasesService: LeasesService,
    private tenantsService: TenantsService) {
  }

  ngOnInit() {
    console.log(this.lease);
  }

  isNew() {
    return typeof this.lease !== 'undefined' && typeof this.lease.id === 'undefined';
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
    this.lease.tenantId = tenant.id;
  }

  unSelectTenant(tenant: Tenant) {
    this.lease.tenantId = undefined;
  }
}
