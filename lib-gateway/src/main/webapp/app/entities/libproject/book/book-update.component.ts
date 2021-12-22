import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, numeric } from 'vuelidate/lib/validators';

import CategoryService from '@/entities/libproject/category/category.service';
import { ICategory } from '@/shared/model/libproject/category.model';

import { IBook, Book } from '@/shared/model/libproject/book.model';
import BookService from './book.service';

const validations: any = {
  book: {
    title: {
      required,
    },
    author: {
      required,
    },
    fine_amount: {
      required,
      numeric,
    },
    publisher: {
      required,
    },
    quantity: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class BookUpdate extends Vue {
  @Inject('bookService') private bookService: () => BookService;
  public book: IBook = new Book();

  @Inject('categoryService') private categoryService: () => CategoryService;

  public categories: ICategory[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bookId) {
        vm.retrieveBook(to.params.bookId);
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
    if (this.book.id) {
      this.bookService()
        .update(this.book)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Book is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.bookService()
        .create(this.book)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Book is created with identifier ' + param.id;
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

  public retrieveBook(bookId): void {
    this.bookService()
      .find(bookId)
      .then(res => {
        this.book = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.categoryService()
      .retrieve()
      .then(res => {
        this.categories = res.data;
      });
  }
}
