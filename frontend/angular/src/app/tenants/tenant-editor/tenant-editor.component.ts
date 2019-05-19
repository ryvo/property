import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { SelectItem } from "primeng/api";
import {TenantsService} from "../tenants.service";
import {Tenant, TenantType} from "../tenant.model";
import {Observable} from "rxjs/internal/Observable";

@Component({
  selector: 'tenant-editor',
  templateUrl: './tenant-editor.component.html',
  styleUrls: ['./tenant-editor.component.scss']
})
export class TenantEditorComponent implements OnInit {

  @Input() tenant: Tenant;
  @Input() visible: boolean = false;
  @Input() disabled: boolean = false;
  @Output() onCancel = new EventEmitter<void>();
  @Output() onSave = new EventEmitter<Tenant>();

  private types: SelectItem[] = [];

  constructor(private tenantsService: TenantsService) { }

  ngOnInit() {
    this.types = this.tenantsService.getTenantTypes();
  }

  isNew() {
    return typeof this.tenant !== 'undefined' && typeof this.tenant.id === 'undefined';
  }

  isLegalPerson() {
    return typeof this.tenant !== 'undefined' && this.tenant.type === TenantType.LEGAL_PERSON;
  }

  isNaturalPerson() {
    return typeof this.tenant !== 'undefined' && this.tenant.type === TenantType.NATURAL_PERSON;
  }

  cancel() {
    this.onCancel.emit();
  }

  submit() {
    let observable: Observable<Tenant>;
    if (this.isNew()) {
      observable = this.tenantsService.createTenant(this.tenant);
    } else {
      observable = this.tenantsService.updateTenant(this.tenant);
    }

    observable.subscribe(
      (response: Tenant) => {
        this.visible = false;
        this.onSave.emit(response);
      },
      (error) => {
        this.visible = true;
      }
    )
  }
}
