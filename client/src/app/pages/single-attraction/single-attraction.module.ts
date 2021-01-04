import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { SingleAttractionPageRoutingModule } from './single-attraction-routing.module';

import { SingleAttractionPage } from './single-attraction.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    SingleAttractionPageRoutingModule
  ],
  declarations: [SingleAttractionPage]
})
export class SingleAttractionPageModule {}
