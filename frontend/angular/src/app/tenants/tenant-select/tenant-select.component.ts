import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Tenant} from "../tenant.model";
import {TenantService} from "../tenant.service";
import {TenantUtils} from "../tenant.utils";

@Component({
  selector: 'tenant-select',
  templateUrl: './tenant-select.component.html',
  styleUrls: ['./tenant-select.component.scss']
})
export class TenantSelectComponent implements OnInit {

  private _selected: any;

  @Input('selected')
  set selected(newTenant: Tenant) {
    this._selected = this.transformTenant(newTenant);
  };

  suggestions: Tenant[];
  query: any;

  @Output()
  onSelect = new EventEmitter<Tenant>();

  @Output()
  onUnSelect = new EventEmitter<Tenant>();

  constructor(private tenantService: TenantService) { }

  ngOnInit() {
  }

  search(event) {
    this.suggestions = undefined;
    if (event.query.length > 3) {
      this.tenantService.searchTenants(event.query)
        .subscribe((tenants: Tenant[]) => {
          this.suggestions = this.transformTenants(tenants)
        });
    } else {
      this.suggestions = [];
    }
  }

  transformTenants(tenants: Tenant[]): any {
    let result = [];
    tenants.forEach((value) => {
      result.push(this.transformTenant(value));
    });
    return result;
  };

  transformTenant(tenant: Tenant) {
    if (tenant) {
      return {
        tenant: tenant,
        displayName: TenantUtils.getDisplayName(tenant),
        displayAddress: TenantUtils.getDisplayAddress(tenant)
      };
    } else {
      return undefined;
    }
  };

  select(value: any) {
    this._selected = value;
    this.onSelect.emit(value.tenant);
  }

  unSelect(value: Tenant) {
    this.query = undefined;
    this._selected = undefined;
    this.onUnSelect.emit(value);
  }
}
