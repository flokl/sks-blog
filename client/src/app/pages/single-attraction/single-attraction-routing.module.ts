import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SingleAttractionPage } from './single-attraction.page';

const routes: Routes = [
  {
    path: '',
    component: SingleAttractionPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SingleAttractionPageRoutingModule {}
