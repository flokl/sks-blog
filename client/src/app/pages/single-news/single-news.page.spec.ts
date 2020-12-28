import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { SingleNewsPage } from './single-news.page';

describe('SingleNewsPage', () => {
  let component: SingleNewsPage;
  let fixture: ComponentFixture<SingleNewsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SingleNewsPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(SingleNewsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
