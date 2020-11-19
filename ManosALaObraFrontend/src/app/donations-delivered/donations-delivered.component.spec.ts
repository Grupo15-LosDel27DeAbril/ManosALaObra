import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonationsDeliveredComponent } from './donations-delivered.component';

describe('HomeComponent', () => {
    let component: DonationsDeliveredComponent;
    let fixture: ComponentFixture<DonationsDeliveredComponent>;
  
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [ DonationsDeliveredComponent ]
      })
      .compileComponents();
    }));
  
    beforeEach(() => {
      fixture = TestBed.createComponent(DonationsDeliveredComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should create', () => {
      expect(component).toBeTruthy();
    });
  });