import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {IonicModule} from '@ionic/angular';

import {SingleNewsPageRoutingModule} from './single-news-routing.module';

import {SingleNewsPage} from './single-news.page';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        SingleNewsPageRoutingModule
    ],
    declarations: [SingleNewsPage]
})
export class SingleNewsPageModule {
}
