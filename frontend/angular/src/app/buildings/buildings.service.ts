import { Injectable } from '@angular/core';
import { ApiService } from "../services/api.service";
import { Building } from "./building.model";
import { Observable } from "rxjs/internal/Observable";
import * as _ from "lodash";

@Injectable({
  providedIn: 'root'
})
export class BuildingsService {

  constructor(private apiService: ApiService) { }

  getBuildings(): Observable<Building[]> {
    return this.apiService.getBuildings();
  }

  createBuilding(building: Building): Observable<Building> {
    return this.apiService.createBuilding(building);
  }

  updateBuilding(building: Building): Observable<Building> {
    let id = building.id;
    let buildingClone = _.clone(building);
    delete buildingClone.id;
    return this.apiService.updateBuilding(id, buildingClone);
  }

  getBuilding(id: number): Observable<Building> {
    return this.apiService.getBuilding(id);
  }

  cloneBuilding(building: Building): Building {
    return _.clone(building);
  }
}
