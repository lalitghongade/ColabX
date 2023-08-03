import { TestBed } from '@angular/core/testing';

import { RecordApiCallsService } from './record-api-calls.service';

describe('RecordApiCallsService', () => {
  let service: RecordApiCallsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecordApiCallsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
