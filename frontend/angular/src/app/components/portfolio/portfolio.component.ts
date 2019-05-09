import { Component, OnInit } from '@angular/core';
import { ApiService } from "../../services/api.service";
import { Observable } from "rxjs/internal/Observable";
import { Building } from "../../models/building.model";
import { Router } from "@angular/router";
import * as _ from "lodash";

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.scss']
})
export class PortfolioComponent implements OnInit {

  private editedBuilding: Building = undefined;
  private buildingFormVisible: boolean = false;
  private buildings: Building[] = [];

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
    this.getListOfBuildings();
  }

  getListOfBuildings(): Observable<Building[]> {
    let $buildings = this.apiService.getListOfBuildings();
    $buildings.subscribe((response: Building[]) => {
      this.buildings = response;
    });
    return $buildings;
  }

  openNewBuildingForm() {
    this.editedBuilding = new Building();
    this.buildingFormVisible = true;
  }

  openBuildingForm(building: Building) {
    this.editedBuilding = _.clone(building);
    this.buildingFormVisible = true;
  }

  cancelBuildingForm() {
    this.buildingFormVisible = false;
    this.editedBuilding = undefined;
  }

  submitBuildingForm() {
    this.buildingFormVisible = false;

    let observable: Observable<Building>;
    if (this.isNew(this.editedBuilding)) {
      observable = this.apiService.createBuilding(this.editedBuilding);
    } else {
      let id = this.editedBuilding.id;
      let building = _.clone(this.editedBuilding);
      delete building.id;
      observable = this.apiService.updateBuilding(id, building);
    }

    observable.subscribe(
      (response) => {
        this.editedBuilding = undefined;
        this.getListOfBuildings();
      },
      (error) => {
        this.buildingFormVisible = true;
      }
    );
  }

  manageBuilding(building: Building) {
    this.router.navigate(['/buildings', building.id]);
  }

  isNew(building: Building) {
    return typeof building !== 'undefined' && typeof building.id === 'undefined';
  }
}
