import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { SingleAuthorPage } from './single-author.page';

describe('SingleAuthorPage', () => {
  let component: SingleAuthorPage;
  let fixture: ComponentFixture<SingleAuthorPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SingleAuthorPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(SingleAuthorPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
