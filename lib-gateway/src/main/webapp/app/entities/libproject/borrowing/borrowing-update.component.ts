import { Component, Vue, Inject } from 'vue-property-decorator';

import BookService from '@/entities/libproject/book/book.service';
import { IBook } from '@/shared/model/libproject/book.model';

import { IBorrowing, Borrowing } from '@/shared/model/libproject/borrowing.model';
import BorrowingService from './borrowing.service';

const validations: any = {
  borrowing: {
    date_borrowed: {},
    due_date: {},
    return_date: {},
    user_id: {},
  },
};

@Component({
  validations,
})
export default class BorrowingUpdate extends Vue {
  @Inject('borrowingService') private borrowingService: () => BorrowingService;
  public borrowing: IBorrowing = new Borrowing();

  @Inject('bookService') private bookService: () => BookService;

  public books: IBook[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.borrowingId) {
        vm.retrieveBorrowing(to.params.borrowingId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.borrowing.id) {
      this.borrowingService()
        .update(this.borrowing)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Borrowing is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.borrowingService()
        .create(this.borrowing)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Borrowing is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveBorrowing(borrowingId): void {
    this.borrowingService()
      .find(borrowingId)
      .then(res => {
        this.borrowing = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.bookService()
      .retrieve()
      .then(res => {
        this.books = res.data;
      });
  }
}
