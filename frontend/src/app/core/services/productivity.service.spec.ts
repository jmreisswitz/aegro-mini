import { TestBed } from '@angular/core/testing';

import { ProductivityService } from './productivity.service';

describe('ProductivityService', () => {
  let service: ProductivityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductivityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
