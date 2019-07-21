import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {Tenant} from "../tenant.model";
import {TenantsService} from "../tenants.service";
import {isNullOrUndefined} from "util";
import {AutoComplete} from "primeng/primeng";

@Component({
  selector: 'tenant-select',
  templateUrl: './tenant-select.component.html',
  styleUrls: ['./tenant-select.component.scss']
})
export class TenantSelectComponent implements OnInit {

  suggestions: Tenant[];
  selected: Tenant;
  query: any;

  @Output()
  onSelect = new EventEmitter<Tenant>();

  @Output()
  onUnSelect = new EventEmitter<Tenant>();

  constructor(private tenantsService: TenantsService) { }

  ngOnInit() {
  }

  search(event) {
    this.suggestions = undefined;
    if (event.query.length > 3) {
      this.tenantsService.searchTenants(event.query)
        .subscribe((tenants: Tenant[]) => {
          this.suggestions = this.transformTenants(tenants)
        });
    } else {
      this.suggestions = [];
    }
  }

  transformTenants(tenants: Tenant[]) {
    let result = [];
    let that = this;
    tenants.forEach(function(value) {
      let item = {
        tenant: value,
        displayName: that.getDisplayName(value),
        displayAddress: that.getDisplayAddress(value)
      };
      result.push(item);
    });
    return result;
  };

  getDisplayName(tenant: Tenant) {
    return [tenant.firstName, tenant.lastName, tenant.companyName].filter(Boolean).join(' ');
  }

  getDisplayAddress(tenant: Tenant) {
    let houseNumber = [tenant.houseNumber, tenant.registryNumber].filter(Boolean).join('/');
    let streetWithHouseNumber = !isNullOrUndefined(tenant.streetName) ? tenant.streetName + ' ' + houseNumber : undefined;
    return [streetWithHouseNumber, tenant.townName, tenant.countryName].filter(Boolean).join(', ');
  }

  select(value: any) {
    this.selected = value;
    this.onSelect.emit(value.tenant);
  }

  unSelect(value: Tenant) {
    this.query = undefined;
    this.selected = undefined;
    this.onUnSelect.emit(value);
  }
}
