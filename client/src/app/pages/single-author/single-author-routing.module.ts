import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SingleAuthorPage } from './single-author.page';

const routes: Routes = [
  {
    path: '',
    component: SingleAuthorPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SingleAuthorPageRoutingModule {}
