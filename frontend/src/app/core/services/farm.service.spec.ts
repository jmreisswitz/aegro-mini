import { TestBed } from '@angular/core/testing';

import { FarmService } from './farm.service';

describe('FarmServiceService', () => {
  let service: FarmService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FarmService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
