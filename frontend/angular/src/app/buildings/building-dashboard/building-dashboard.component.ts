import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from "@angular/router";
import { Building } from "../building.model";
import { switchMap } from "rxjs/operators";
import { Observable } from "rxjs/internal/Observable";
import { BuildingsService } from "../buildings.service";

@Component({
  selector: 'building-dashboard',
  templateUrl: './building-dashboard.component.html',
  styleUrls: ['./building-dashboard.component.scss']
})
export class BuildingDashboardComponent implements OnInit {

  private id: number;
  private $building: Observable<Building>;

  constructor(private buildingService: BuildingsService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.$building = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => {
        this.id = Number(params.get('id'));
        return this.buildingService.getBuilding(this.id);
      })
    )
  }

}
