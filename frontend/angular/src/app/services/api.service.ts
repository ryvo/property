import { Injectable } from '@angular/core';
import { Building } from '../models/building.model';
import { Observable } from 'rxjs/internal/Observable';
import { HttpErrorResponse } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs/internal/observable/throwError';
import { MessageService } from 'primeng/api';
import { HttpService } from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpService, private messageService: MessageService) {
    console.log("Aaa");
  }

  private sendRequest<T>(method: string, url: string, body?: any): Observable<T> {
    return this.http.sendRequest(method, url, body);

/*
    return this.http.request<T>(method, url, options).pipe(catchError((error: HttpErrorResponse) => {
      console.log(error);
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'API Error'
      });
      return throwError(error);
    }));
*/
  }

  public getListOfBuildings() {
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
}
