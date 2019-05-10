import { Routes } from "@angular/router";
import { BuildingListComponent } from "./buildings/building-list/building-list.component";
import { BuildingDashboardComponent } from "./buildings/building-dashboard/building-dashboard.component";

export const routes: Routes = [
  { path: 'buildings', component: BuildingListComponent },
  { path: 'buildings/:id', component: BuildingDashboardComponent },
  { path: '', redirectTo: '/buildings', pathMatch: 'full' }
];
