import { IBook } from '@/shared/model/libproject/book.model';

export interface IBorrowing {
  id?: number;
  date_borrowed?: Date | null;
  due_date?: Date | null;
  return_date?: Date | null;
  user_id?: number | null;
  book?: IBook | null;
}

export class Borrowing implements IBorrowing {
  constructor(
    public id?: number,
    public date_borrowed?: Date | null,
    public due_date?: Date | null,
    public return_date?: Date | null,
    public user_id?: number | null,
    public book?: IBook | null
  ) {}
}
