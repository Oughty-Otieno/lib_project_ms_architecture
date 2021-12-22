import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBorrowing } from '@/shared/model/libproject/borrowing.model';
import BorrowingService from './borrowing.service';

@Component
export default class BorrowingDetails extends Vue {
  @Inject('borrowingService') private borrowingService: () => BorrowingService;
  public borrowing: IBorrowing = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.borrowingId) {
        vm.retrieveBorrowing(to.params.borrowingId);
      }
    });
  }

  public retrieveBorrowing(borrowingId) {
    this.borrowingService()
      .find(borrowingId)
      .then(res => {
        this.borrowing = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
