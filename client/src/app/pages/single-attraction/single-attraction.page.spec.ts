import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { SingleAttractionPage } from './single-attraction.page';

describe('SingleAttractionPage', () => {
  let component: SingleAttractionPage;
  let fixture: ComponentFixture<SingleAttractionPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SingleAttractionPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(SingleAttractionPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
