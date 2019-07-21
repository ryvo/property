import { Component, OnInit } from '@angular/core';
import {Tenant, TenantType} from "../tenant.model";
import {TenantsService} from "../tenants.service";
import {Observable} from "rxjs/internal/Observable";
import {Building} from "../../buildings/building.model";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-tenant-list',
  templateUrl: './tenant-list.component.html',
  styleUrls: ['./tenant-list.component.scss']
})
export class TenantListComponent implements OnInit {

  private editedTenant;
  private tenantEditorVisible = false;
  private suggestedTenants: Tenant[];
  private $tenants: Observable<Tenant[]>;

  constructor(private tenantsService: TenantsService) { }

  ngOnInit() {
  }

  fetchTenants() {
    //this.$tenants = this.tenantsService.
  }

  searchTenants(event) {
    this.suggestedTenants = [];
    this.tenantsService.searchTenants(event.query)
      .subscribe(
      (response: Tenant[]) => {
        this.suggestedTenants = response;
      }
    )
  }

  isNew() {
    return (typeof this.editedTenant != 'undefined') && (typeof this.editedTenant.id != 'undefined');
  }

  startNewTenant() {
    this.editedTenant = new Tenant();
    this.editedTenant.type = TenantType.NATURAL_PERSON;
    this.tenantEditorVisible = true;
  }

  onCancelTenantEditor() {
    this.tenantEditorVisible = false;
    this.editedTenant = undefined;
  }

  onSaveTenantEditor() {
    this.tenantEditorVisible = false;
    let observable: Observable<Tenant>;
    if (this.isNew()) {
      observable = this.tenantsService.createTenant(this.editedTenant);
    } else {
      observable = this.tenantsService.updateTenant(this.editedTenant);
    }

    observable.subscribe(
      (response) => {
        this.editedTenant = undefined;
        this.fetchTenants();
      },
      (error) => {
        this.tenantEditorVisible = true;
      }
    );

  }
}
