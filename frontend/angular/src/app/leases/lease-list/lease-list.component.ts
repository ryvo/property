import {Component, OnDestroy, OnInit} from '@angular/core';
import {LeasesService} from "../leases.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Lease} from "../lease.model";
import {Observable} from "rxjs/internal/Observable";
import {BuildingUnit} from "../../building-units/building-unit.model";
import {BuildingUnitsService} from "../../building-units/building-units.service";

@Component({
  selector: 'lease-list',
  templateUrl: './lease-list.component.html',
  styleUrls: ['./lease-list.component.scss']
})
export class LeaseListComponent implements OnInit, OnDestroy {

  private buildingUnitId: number;
  private $buildingUnit: Observable<BuildingUnit>;
  private $leases: Observable<Lease[]>;
  private editedLease: Lease;
  private leaseEditorVisible: boolean = false;

  constructor(
    private buildingUnitsService: BuildingUnitsService,
    private leasesService: LeasesService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.buildingUnitId = Number(params.get('id'));
      this.fetchBuildingUnitDetail();
      this.fetchLeases();
    });
  }

  ngOnDestroy() {

  }

  fetchBuildingUnitDetail() {
    this.$buildingUnit = this.buildingUnitsService.getBuildingUnit(this.buildingUnitId);
  }

  fetchLeases() {
    this.$leases = this.leasesService.getLeases(this.buildingUnitId);
  }

  startNewLease() {
    this.editedLease = new Lease();
    this.leaseEditorVisible = true;
  }

  onLeaseEditorSaved(lease: Lease) {
    this.editedLease = undefined;
    this.fetchLeases();
  }

  onLeaseEditorCancel() {
    this.editedLease = undefined;
  }
}
