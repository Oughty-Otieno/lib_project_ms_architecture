/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import BorrowingDetailComponent from '@/entities/libproject/borrowing/borrowing-details.vue';
import BorrowingClass from '@/entities/libproject/borrowing/borrowing-details.component';
import BorrowingService from '@/entities/libproject/borrowing/borrowing.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Borrowing Management Detail Component', () => {
    let wrapper: Wrapper<BorrowingClass>;
    let comp: BorrowingClass;
    let borrowingServiceStub: SinonStubbedInstance<BorrowingService>;

    beforeEach(() => {
      borrowingServiceStub = sinon.createStubInstance<BorrowingService>(BorrowingService);

      wrapper = shallowMount<BorrowingClass>(BorrowingDetailComponent, {
        store,
        localVue,
        router,
        provide: { borrowingService: () => borrowingServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBorrowing = { id: 123 };
        borrowingServiceStub.find.resolves(foundBorrowing);

        // WHEN
        comp.retrieveBorrowing(123);
        await comp.$nextTick();

        // THEN
        expect(comp.borrowing).toBe(foundBorrowing);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBorrowing = { id: 123 };
        borrowingServiceStub.find.resolves(foundBorrowing);

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
