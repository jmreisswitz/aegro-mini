import { TestBed } from '@angular/core/testing';

import { FieldInMemoryService } from './field-in-memory.service';

describe('FieldInMemoryService', () => {
  let service: FieldInMemoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FieldInMemoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
