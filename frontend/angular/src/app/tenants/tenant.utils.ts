import {Tenant, TenantType} from "./tenant.model";
import * as _ from "lodash";
import {isNullOrUndefined} from "util";

export class TenantUtils {

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
