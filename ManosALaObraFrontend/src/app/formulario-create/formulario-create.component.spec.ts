import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioCreateComponent } from './formulario-create.component';

describe('DonacionCreateComponent', () => {
  let component: FormularioCreateComponent;
  let fixture: ComponentFixture<FormularioCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});