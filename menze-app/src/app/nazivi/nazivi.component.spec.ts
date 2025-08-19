import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaziviComponent } from './nazivi.component';

describe('NaziviComponent', () => {
  let component: NaziviComponent;
  let fixture: ComponentFixture<NaziviComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NaziviComponent]
    });
    fixture = TestBed.createComponent(NaziviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
