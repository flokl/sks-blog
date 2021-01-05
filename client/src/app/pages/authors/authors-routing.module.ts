import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AuthorsPage} from './authors.page';

const routes: Routes = [
    {
        path: '',
        component: AuthorsPage
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AuthorsPageRoutingModule {
}
