import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from "@angular/router";
import { Building } from "../../models/building.model";
import { switchMap } from "rxjs/operators";
import { ApiService } from "../../services/api.service";
import { Observable } from "rxjs/internal/Observable";

@Component({
  selector: 'app-building',
  templateUrl: './building.component.html',
  styleUrls: ['./building.component.scss']
})
export class BuildingComponent implements OnInit {

  private id: number;
  private $building: Observable<Building>;

  constructor(private api: ApiService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.$building = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => {
        this.id = Number(params.get('id'));
        return this.api.getBuilding(this.id);
      })
    )
  }

}
