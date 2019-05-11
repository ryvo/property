import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from "@angular/router";
import { Building } from "../building.model";
import {switchMap, tap} from "rxjs/operators";
import { Observable } from "rxjs/internal/Observable";
import { BuildingsService } from "../buildings.service";
import {BuildingUnit} from "../../building-units/building-unit.model";
import {BuildingUnitsService} from "../../building-units/building-units.service";
import {of} from "rxjs/internal/observable/of";

@Component({
  selector: 'building-dashboard',
  templateUrl: './building-dashboard.component.html',
  styleUrls: ['./building-dashboard.component.scss']
})
export class BuildingDashboardComponent implements OnInit {

  private buildingId: number;
  private $building: Observable<Building>;
  private $buildingUnits: Observable<BuildingUnit[]>;
  private editedBuildingUnit;
  private buildingUnitEditorVisible = false;

  constructor(
    private buildingService: BuildingsService,
    private buildingUnitsService: BuildingUnitsService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.$building = this.route.paramMap.pipe(
      tap((params: ParamMap) => {
        this.buildingId = Number(params.get('id'));
      }),
      switchMap((params: ParamMap) => {
        return this.buildingService.getBuilding(this.buildingId);
      })
    );

    this.$buildingUnits = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => {
        let buildingId = Number(params.get('id'));
        return this.buildingUnitsService.getBuildingUnits(buildingId);
      })
    );
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
    this.$buildingUnits = of(this.buildingId).pipe(
      switchMap((buildingId) => this.buildingUnitsService.getBuildingUnits(buildingId))
    );
  }
}
