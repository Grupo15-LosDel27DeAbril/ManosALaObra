import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonacionCreateComponent } from './donacion-create.component';

describe('DonacionCreateComponent', () => {
  let component: DonacionCreateComponent;
  let fixture: ComponentFixture<DonacionCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonacionCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonacionCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});