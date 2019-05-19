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
    return this.apiService.createLease(buildingUnitId, lease);
  }

  updateLease(lease: Lease): Observable<Lease> {
    let id = lease.id;
    let leaseClone = LeasesService.cloneLease(lease);
    delete lease.id;
    return this.apiService.updateLease(id, leaseClone);
  }

  static cloneLease(lease: Lease): Lease {
    return _.clone(lease);
  }
}
