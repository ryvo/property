import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaseListComponent } from './lease-list.component';

describe('LeaseListComponent', () => {
  let component: LeaseListComponent;
  let fixture: ComponentFixture<LeaseListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeaseListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeaseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
