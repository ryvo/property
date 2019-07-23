import {Injectable} from '@angular/core';
import {ApiService} from "../services/api.service";
import {Observable} from "rxjs/internal/Observable";
import {Lease} from "./lease.model";
import * as _ from "lodash";

@Injectable({
  providedIn: 'root'
})
export class LeasesService {

  constructor(private apiService: ApiService) { }

  getLeases(buildingUnitId: number): Observable<Lease[]> {
    return this.apiService.getLeases(buildingUnitId);
  }

  createLease(buildingUnitId: number, lease: Lease): Observable<Lease> {
    let leaseClone = _.clone(lease);
    delete leaseClone.id;
    return this.apiService.createLease(buildingUnitId, leaseClone);
  }

  updateLease(lease: Lease): Observable<Lease> {
    let id = lease.id;
    let leaseClone = _.clone(lease);
    delete leaseClone.id;
    return this.apiService.updateLease(id, leaseClone);
  }
}
