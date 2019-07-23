import {Injectable} from '@angular/core';
import {ApiService} from "../services/api.service";
import {Observable} from "rxjs/internal/Observable";
import {Tenant, TenantType} from "./tenant.model";
import * as _ from "lodash";
import {SelectItem} from "primeng/api";
import {isNullOrUndefined} from "util";

@Injectable({
  providedIn: 'root'
})
export class TenantService {

  constructor(private apiService: ApiService) { }

  getTenantTypes(): SelectItem[] {
    let types: SelectItem[] = [];
    for (let type in TenantType) {
      types.push({
        label: TenantType[type],
        value: type
      });
    }
    return types;
  }

  createTenant(tenant: Tenant): Observable<Tenant> {
    let tenantClone = TenantService.cloneTenant(tenant);
    delete tenantClone.id;
    return this.apiService.createTenant(tenant);
  }

  updateTenant(tenant: Tenant): Observable<Tenant> {
    return null;
  }

  searchTenants(query: string): Observable<Tenant[]> {
    return this.apiService.searchTenants(query);
  }

  public getTenant(id: number): Observable<Tenant> {
    return this.apiService.getTenant(id);
  }

  static cloneTenant(tenant: Tenant): Tenant {
    return _.clone(tenant);
  }

  static getDisplayName(tenant: Tenant) {
    return [tenant.firstName, tenant.lastName, tenant.companyName].filter(Boolean).join(' ');
  }

  static getDisplayAddress(tenant: Tenant) {
    let houseNumber = [tenant.houseNumber, tenant.registryNumber].filter(Boolean).join('/');
    let streetWithHouseNumber = !isNullOrUndefined(tenant.streetName) ? tenant.streetName + ' ' + houseNumber : undefined;
    return [streetWithHouseNumber, tenant.townName, tenant.countryName].filter(Boolean).join(', ');
  }
}
