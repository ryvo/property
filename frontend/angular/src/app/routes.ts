import { Routes } from "@angular/router";
import { BuildingListComponent } from "./buildings/building-list/building-list.component";
import { BuildingDashboardComponent } from "./buildings/building-dashboard/building-dashboard.component";
import {LeaseListComponent} from "./leases/lease-list/lease-list.component";
import {TenantListComponent} from "./tenants/tenant-list/tenant-list.component";

export const routes: Routes = [
  { path: 'buildings', component: BuildingListComponent },
  { path: 'buildings/:id', component: BuildingDashboardComponent },
  { path: 'building-units/:id/leases', component: LeaseListComponent },
  { path: 'tenants', component: TenantListComponent },
  { path: '', redirectTo: '/buildings', pathMatch: 'full' }
];
