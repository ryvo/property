import { Injectable } from '@angular/core';
import {Building} from "../buildings/building.model";
import {ApiService} from "../services/api.service";
import {Observable} from "rxjs/internal/Observable";
import {BuildingUnit, BuildingUnitType} from "./building-unit.model";
import * as _ from "lodash";

@Injectable({
  providedIn: 'root'
})
export class BuildingUnitsService {

  constructor(private apiService: ApiService) { }

  getBuildingUnitTypes() {
    let types = [];
    for (let type in BuildingUnitType) {
      types.push({
        name: type,
        code: type
      });
    }
    return types;
  }

  getBuildingUnits(buildingId: number): Observable<BuildingUnit[]> {
    return this.apiService.getBuildingUnits(buildingId);
  }

  createBuildingUnit(buildingId: number, buildingUnit: BuildingUnit): Observable<BuildingUnit> {
    return this.apiService.createBuildingUnit(buildingId, buildingUnit);
  }

  updateBuilding(buildingId: number, buildingUnit: BuildingUnit): Observable<BuildingUnit> {
    let buildingUnitId = buildingUnit.id;
    let buildingUnitClone = BuildingUnitsService.cloneBuildingUnit(buildingUnit);
    delete buildingUnitClone.id;
    return this.apiService.updateBuildingUnit(buildingId, buildingUnitId, buildingUnitClone);
  }

  getBuildingUnit(buildingId: number, buildingUnitId: number): Observable<BuildingUnit> {
    return this.apiService.getBuildingUnit(buildingId, buildingUnitId);
  }

  static cloneBuildingUnit(buildingUnit: BuildingUnit): BuildingUnit {
    return _.clone(buildingUnit);
  }
}
