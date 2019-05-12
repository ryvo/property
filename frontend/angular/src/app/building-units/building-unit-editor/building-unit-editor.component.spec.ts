import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuildingUnitEditorComponent } from './building-unit-editor.component';

describe('BuildingUnitEditorComponent', () => {
  let component: BuildingUnitEditorComponent;
  let fixture: ComponentFixture<BuildingUnitEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuildingUnitEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuildingUnitEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
