import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmDetailComponent } from './farm-detail.component';

describe('FarmDetailComponent', () => {
  let component: FarmDetailComponent;
  let fixture: ComponentFixture<FarmDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FarmDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
