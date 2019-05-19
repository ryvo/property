import { TestBed } from '@angular/core/testing';

import { LeasesService } from './leases.service';

describe('TenantsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LeasesService = TestBed.get(LeasesService);
    expect(service).toBeTruthy();
  });
});
