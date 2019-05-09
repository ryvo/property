import { Routes } from "@angular/router";
import { PortfolioComponent } from "./components/portfolio/portfolio.component";
import { BuildingComponent } from "./components/building/building.component";

export const routes: Routes = [
  { path: 'buildings', component: PortfolioComponent },
  { path: 'buildings/:id', component: BuildingComponent },
  { path: '', redirectTo: '/buildings', pathMatch: 'full' }
];
