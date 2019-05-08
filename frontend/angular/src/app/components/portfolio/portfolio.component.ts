import { Component, OnInit } from '@angular/core';
import { ApiService } from "../../services/api.service";
import { Observable } from "rxjs/internal/Observable";
import { Building } from "../../models/building.model";

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.scss']
})
export class PortfolioComponent implements OnInit {

  private newBuilding: Building = undefined;
  private displayNewBuildingDialog: boolean = false;
  private buildings: Building[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.getListOfBuildings();
  }

  getListOfBuildings(): Observable<Building[]> {
    let $buildings = this.apiService.getListOfBuildings();
    $buildings.subscribe((response: Building[]) => {
      console.log(response);
      this.buildings = response;
    });
    return $buildings;
  }

  startNewBuilding() {
    this.newBuilding = new Building();
    this.displayNewBuildingDialog = true;
  }

  cancelNewBuilding() {
    this.displayNewBuildingDialog = false;
    this.newBuilding = undefined;
  }

  submitNewBuilding() {
    this.displayNewBuildingDialog = false;
    this.apiService.createBuilding(this.newBuilding).subscribe(
      (response) => {
        this.newBuilding = undefined;
        this.buildings = response;
        this.getListOfBuildings();
      },
      (error) => {
        this.displayNewBuildingDialog = true;
      }
    );
  }

  addressLine1(building: Building) {

  }
}
