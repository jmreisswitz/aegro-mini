import { TestBed } from '@angular/core/testing';

import { FieldServiceService } from './field-service.service';

describe('FieldServiceService', () => {
  let service: FieldServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FieldServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
