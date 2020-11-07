import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailDonationsComponent } from './email-donations.comonent';

describe('EmailDonationsComponent', () => {
  let component: EmailDonationsComponent;
  let fixture: ComponentFixture<EmailDonationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmailDonationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailDonationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});