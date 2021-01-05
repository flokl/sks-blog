import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'news',
    pathMatch: 'full'
  },
  {
    path: 'news',
    loadChildren: () => import('./pages/news/news.module').then( m => m.NewsPageModule)
  },
  {
    path: 'news/:id',
    loadChildren: () => import('./pages/single-news/single-news.module').then( m => m.SingleNewsPageModule)
  },
  {
    path: 'categories',
    loadChildren: () => import('./pages/categories/categories.module').then( m => m.CategoriesPageModule)
  },
  {
    path: 'categories/:id',
    loadChildren: () => import('./pages/single-category/single-category.module').then( m => m.SingleCategoryPageModule)
  },
  {
    path: 'authors',
    loadChildren: () => import('./pages/authors/authors.module').then( m => m.AuthorsPageModule)
  },
  {
    path: 'authors/:id',
    loadChildren: () => import('./pages/single-author/single-author.module').then( m => m.SingleAuthorPageModule)
  },
  {
    path: 'create-entry',
    loadChildren: () => import('./pages/create-entry/create-entry.module').then( m => m.CreateEntryPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules, useHash: true })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
