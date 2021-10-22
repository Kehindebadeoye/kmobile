import { TestBed } from '@angular/core/testing';

import { KmobileService } from './kmobile.service';

describe('KmobileService', () => {
  let service: KmobileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KmobileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
