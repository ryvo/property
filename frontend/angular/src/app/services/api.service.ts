import { Injectable } from '@angular/core';
import { Building } from '../buildings/building.model';
import { Observable } from 'rxjs/internal/Observable';
import { HttpErrorResponse } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs/internal/observable/throwError';
import { MessageService } from 'primeng/api';
import { HttpService } from "./http.service";
import {BuildingUnit} from "../building-units/building-unit.model";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpService, private messageService: MessageService) {
    console.log("Aaa");
  }

  private sendRequest<T>(method: string, url: string, body?: any): Observable<T> {
    return this.http.sendRequest(method, url, body);
  }


  /* BUILDINGS */

  public getBuildings() {
    return this.sendRequest<Building[]>('get', '/api/v1/buildings');
  }

  public createBuilding(building: Building): Observable<Building> {
    return this.sendRequest<Building>('post', '/api/v1/buildings', building);
  }

  public updateBuilding(id: number, building: Building): Observable<Building> {
    return this.sendRequest<Building>('put', '/api/v1/buildings/' + id, building);
  }

  public getBuilding(id: number): Observable<Building> {
    return this.sendRequest<Building>('get', '/api/v1/buildings/' + id);
  }


  /* BUILDING UNITS */

  public getBuildingUnits(buildingId: number) {
    return this.sendRequest<BuildingUnit[]>('get', '/api/v1/buildings/' + buildingId + '/units');
  }

  public createBuildingUnit(buildingId: number, buildingUnit: BuildingUnit): Observable<BuildingUnit> {
    return this.sendRequest<BuildingUnit>('post', '/api/v1/buildings/' + buildingId + '/units', buildingUnit);
  }

  public updateBuildingUnit(buildingId: number, buildingUnitId: number, buildingUnit: BuildingUnit): Observable<BuildingUnit> {
    return this.sendRequest<BuildingUnit>('put', '/api/v1/buildings/' + buildingId + '/units/' + buildingUnitId, buildingUnit);
  }

  public getBuildingUnit(buildingId: number, buildingUnitId: number): Observable<BuildingUnit> {
    return this.sendRequest<BuildingUnit>('get', '/api/v1/buildings/' + buildingId + '/units/' + buildingUnitId);
  }

}
