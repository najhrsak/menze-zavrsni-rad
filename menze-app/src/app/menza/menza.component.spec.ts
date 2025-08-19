import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenzaComponent } from './menza.component';

describe('MenzaComponent', () => {
  let component: MenzaComponent;
  let fixture: ComponentFixture<MenzaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MenzaComponent]
    });
    fixture = TestBed.createComponent(MenzaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
