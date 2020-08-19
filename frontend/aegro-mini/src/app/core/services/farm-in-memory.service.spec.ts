import { TestBed } from '@angular/core/testing';

import { FarmInMemoryService } from './farm-in-memory.service';

describe('FarmInMemoryService', () => {
  let service: FarmInMemoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FarmInMemoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
