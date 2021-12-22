/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import BorrowingUpdateComponent from '@/entities/libproject/borrowing/borrowing-update.vue';
import BorrowingClass from '@/entities/libproject/borrowing/borrowing-update.component';
import BorrowingService from '@/entities/libproject/borrowing/borrowing.service';

import BookService from '@/entities/libproject/book/book.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Borrowing Management Update Component', () => {
    let wrapper: Wrapper<BorrowingClass>;
    let comp: BorrowingClass;
    let borrowingServiceStub: SinonStubbedInstance<BorrowingService>;

    beforeEach(() => {
      borrowingServiceStub = sinon.createStubInstance<BorrowingService>(BorrowingService);

      wrapper = shallowMount<BorrowingClass>(BorrowingUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          borrowingService: () => borrowingServiceStub,

          bookService: () => new BookService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.borrowing = entity;
        borrowingServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(borrowingServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.borrowing = entity;
        borrowingServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(borrowingServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBorrowing = { id: 123 };
        borrowingServiceStub.find.resolves(foundBorrowing);
        borrowingServiceStub.retrieve.resolves([foundBorrowing]);

        // WHEN
        comp.beforeRouteEnter({ params: { borrowingId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.borrowing).toBe(foundBorrowing);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
