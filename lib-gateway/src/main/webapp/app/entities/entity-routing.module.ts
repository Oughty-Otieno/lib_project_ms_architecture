import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'category',
        data: { pageTitle: 'Categories' },
        loadChildren: () => import('./libproject/category/category.module').then(m => m.LibprojectCategoryModule),
      },
      {
        path: 'book',
        data: { pageTitle: 'Books' },
        loadChildren: () => import('./libproject/book/book.module').then(m => m.LibprojectBookModule),
      },
      {
        path: 'borrowing',
        data: { pageTitle: 'Borrowings' },
        loadChildren: () => import('./libproject/borrowing/borrowing.module').then(m => m.LibprojectBorrowingModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
