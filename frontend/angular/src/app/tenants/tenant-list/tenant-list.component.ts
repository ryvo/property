import { Component, OnInit } from '@angular/core';
import {Tenant, TenantType} from "../tenant.model";

@Component({
  selector: 'app-tenant-list',
  templateUrl: './tenant-list.component.html',
  styleUrls: ['./tenant-list.component.scss']
})
export class TenantListComponent implements OnInit {

  private editedTenant;
  private tenantEditorVisible = false;

  constructor() { }

  ngOnInit() {
  }

  startNewTenant() {
    this.editedTenant = new Tenant();
    this.editedTenant.type = TenantType.NATURAL_PERSON;
    this.tenantEditorVisible = true;
  }
}
