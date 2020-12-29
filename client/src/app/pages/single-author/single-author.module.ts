import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { SingleAuthorPageRoutingModule } from './single-author-routing.module';

import { SingleAuthorPage } from './single-author.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    SingleAuthorPageRoutingModule
  ],
  declarations: [SingleAuthorPage]
})
export class SingleAuthorPageModule {}
