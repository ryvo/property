import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuildingDashboardComponent } from './building-dashboard.component';

describe('BuildingDashboardComponent', () => {
  let component: BuildingDashboardComponent;
  let fixture: ComponentFixture<BuildingDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuildingDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuildingDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
