import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Category = () => import('@/entities/libproject/category/category.vue');
// prettier-ignore
const CategoryUpdate = () => import('@/entities/libproject/category/category-update.vue');
// prettier-ignore
const CategoryDetails = () => import('@/entities/libproject/category/category-details.vue');
// prettier-ignore
const Book = () => import('@/entities/libproject/book/book.vue');
// prettier-ignore
const BookUpdate = () => import('@/entities/libproject/book/book-update.vue');
// prettier-ignore
const BookDetails = () => import('@/entities/libproject/book/book-details.vue');
// prettier-ignore
const Borrowing = () => import('@/entities/libproject/borrowing/borrowing.vue');
// prettier-ignore
const BorrowingUpdate = () => import('@/entities/libproject/borrowing/borrowing-update.vue');
// prettier-ignore
const BorrowingDetails = () => import('@/entities/libproject/borrowing/borrowing-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/category',
    name: 'Category',
    component: Category,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/category/new',
    name: 'CategoryCreate',
    component: CategoryUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/category/:categoryId/edit',
    name: 'CategoryEdit',
    component: CategoryUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/category/:categoryId/view',
    name: 'CategoryView',
    component: CategoryDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book',
    name: 'Book',
    component: Book,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/new',
    name: 'BookCreate',
    component: BookUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/:bookId/edit',
    name: 'BookEdit',
    component: BookUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/:bookId/view',
    name: 'BookView',
    component: BookDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/borrowing',
    name: 'Borrowing',
    component: Borrowing,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/borrowing/new',
    name: 'BorrowingCreate',
    component: BorrowingUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/borrowing/:borrowingId/edit',
    name: 'BorrowingEdit',
    component: BorrowingUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/borrowing/:borrowingId/view',
    name: 'BorrowingView',
    component: BorrowingDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
