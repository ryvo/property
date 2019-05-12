import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BuildingUnit, BuildingUnitType} from "../building-unit.model";
import {BuildingUnitsService} from "../building-units.service";
import {Observable} from "rxjs/internal/Observable";
import {SelectItem} from 'primeng/api';

@Component({
  selector: 'building-unit-editor',
  templateUrl: './building-unit-editor.component.html',
  styleUrls: ['./building-unit-editor.component.scss']
})
export class BuildingUnitEditorComponent implements OnInit {

  @Input() buildingId: number;
  @Input() buildingUnit: BuildingUnit;
  @Input() visible: boolean = false;
  @Input() disabled: boolean = false;
  @Output() onCancel = new EventEmitter<void>();
  @Output() onSave = new EventEmitter<BuildingUnit>();

  private types: SelectItem[] = [];

  constructor(private buildingUnitsService: BuildingUnitsService) { }

  ngOnInit() {
    this.types = this.buildingUnitsService.getBuildingUnitTypes();
  }

  isNew(buildingUnit: BuildingUnit) {
    return typeof buildingUnit !== 'undefined' && typeof buildingUnit.id === 'undefined';
  }

  cancel() {
    this.onCancel.emit();
  }

  submit() {
    let observable: Observable<BuildingUnit>;
    if (this.isNew(this.buildingUnit)) {
      observable = this.buildingUnitsService.createBuildingUnit(this.buildingId, this.buildingUnit);
    } else {
      observable = this.buildingUnitsService.updateBuilding(this.buildingId, this.buildingUnit);
    }

    observable.subscribe(
      (response) => {
        this.visible = false;
        this.onSave.emit(this.buildingUnit);
      },
      (error) => {
        this.visible = true;
      }
    )
  }
}
