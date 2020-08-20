import { TestBed } from '@angular/core/testing';

import { FarmServiceService } from './farm-service.service';

describe('FarmServiceService', () => {
  let service: FarmServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FarmServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
