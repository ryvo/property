import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from "@angular/router";
import { Building } from "../building.model";
import {switchMap, tap} from "rxjs/operators";
import { Observable } from "rxjs/internal/Observable";
import { BuildingsService } from "../buildings.service";
import {BuildingUnit} from "../../building-units/building-unit.model";
import {BuildingUnitsService} from "../../building-units/building-units.service";
import {of} from "rxjs/internal/observable/of";
import {forkJoin} from "rxjs/internal/observable/forkJoin";
import {Subscription} from "rxjs/internal/Subscription";
import {Subscriber} from "rxjs/internal-compatibility";
import {Subject} from "rxjs/internal/Subject";

@Component({
  selector: 'building-dashboard',
  templateUrl: './building-dashboard.component.html',
  styleUrls: ['./building-dashboard.component.scss']
})
export class BuildingDashboardComponent implements OnInit {

  private buildingId: number;
  private $building: Subject<Building> = new Subject<Building>();
  private $buildingUnits: Subject<BuildingUnit[]> = new Subject<BuildingUnit[]>();
  private editedBuildingUnit;
  private buildingUnitEditorVisible = false;

  constructor(
    private buildingService: BuildingsService,
    private buildingUnitsService: BuildingUnitsService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
        let buildingId: number = Number(params.get('id'));
        this.buildingId = buildingId;
        this.fetchBuildingDetail();
        this.fetchBuildingUnits();
    });
  }

  fetchBuildingDetail() {
    this.buildingService
      .getBuilding(this.buildingId)
      .subscribe((building: Building) => this.$building.next(building));
  }

  fetchBuildingUnits() {
    this.buildingUnitsService
      .getBuildingUnits(this.buildingId)
      .subscribe((buildingUnits: BuildingUnit[]) => this.$buildingUnits.next(buildingUnits));
  }

  startNewBuildingUnit() {
    this.editedBuildingUnit = new BuildingUnit();
    this.buildingUnitEditorVisible = true;
  }

  onCancelBuildingUnitEditor() {
    this.buildingUnitEditorVisible = false;
  }

  onSaveBuildingUnitEditor() {
    this.buildingUnitEditorVisible = false;
    this.editedBuildingUnit = undefined;
    this.fetchBuildingUnits();
  }
}
