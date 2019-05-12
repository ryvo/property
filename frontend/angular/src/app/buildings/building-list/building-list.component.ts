import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs/internal/Observable";
import { Building } from "../building.model";
import { Router } from "@angular/router";
import { BuildingsService } from "../buildings.service";

@Component({
  selector: 'building-list',
  templateUrl: './building-list.component.html',
  styleUrls: ['./building-list.component.scss']
})
export class BuildingListComponent implements OnInit {

  private editedBuilding: Building = undefined;
  private buildingFormVisible: boolean = false;
  private $buildings: Observable<Building[]>;

  constructor(private buildingService: BuildingsService, private router: Router) { }

  ngOnInit() {
    this.fetchBuildings();
  }

  fetchBuildings() {
    this.$buildings = this.buildingService.getBuildings();
  }

  openNewBuildingForm() {
    this.editedBuilding = new Building();
    this.buildingFormVisible = true;
  }

  openBuildingForm(building: Building) {
    this.editedBuilding = BuildingsService.cloneBuilding(building);
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
      observable = this.buildingService.createBuilding(this.editedBuilding);
    } else {
      observable = this.buildingService.updateBuilding(this.editedBuilding);
    }

    observable.subscribe(
      (response) => {
        this.editedBuilding = undefined;
        this.fetchBuildings();
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
