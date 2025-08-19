import { TestBed } from '@angular/core/testing';

import { MenzaService } from './menza.service';

describe('MenzaService', () => {
  let service: MenzaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenzaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
