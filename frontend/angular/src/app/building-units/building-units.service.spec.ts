import { TestBed } from '@angular/core/testing';

import { BuildingUnitsService } from './building-units.service';

describe('BuildingUnitsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuildingUnitsService = TestBed.get(BuildingUnitsService);
    expect(service).toBeTruthy();
  });
});
