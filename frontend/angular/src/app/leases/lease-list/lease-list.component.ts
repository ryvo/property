import {Component, OnDestroy, OnInit} from '@angular/core';
import {LeasesService} from "../leases.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Lease} from "../lease.model";
import {Observable} from "rxjs/internal/Observable";
import {BuildingUnit} from "../../building-units/building-unit.model";
import {BuildingUnitsService} from "../../building-units/building-units.service";
import {TenantUtils} from'../../tenants/tenant.utils';
import {Tenant} from "../../tenants/tenant.model";

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
    this.$leases.subscribe(
      leases => console.log(leases)
    );
  }

  startNewLease() {
    this.editedLease = new Lease();
    this.leaseEditorVisible = true;
  }

  editLease(lease: Lease) {
    this.editedLease = lease;
    this.leaseEditorVisible = true;
  }

  tenantName(lease: Lease): string {
    return (lease && lease.te) ? TenantUtils.getDisplayName(tenant) : undefined;
  }

  onLeaseEditorSaved() {
    this.editedLease = undefined;
    this.fetchLeases();
  }

  onLeaseEditorCancel() {
    this.editedLease = undefined;
  }
}
