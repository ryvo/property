import {Injectable} from '@angular/core';
import {ApiService} from "../services/api.service";
import {Observable} from "rxjs/internal/Observable";
import {Tenant, TenantType} from "./tenant.model";
import * as _ from "lodash";
import {SelectItem} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class TenantsService {

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
    let tenantClone = TenantsService.cloneTenant(tenant);
    delete tenantClone.id;
    return this.apiService.createTenant(tenant);
  }

  updateTenant(tenant: Tenant): Observable<Tenant> {
    return null;
  }

  static cloneTenant(tenant: Tenant): Tenant {
    return _.clone(tenant);
  }
}
