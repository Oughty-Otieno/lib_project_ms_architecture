import { ICategory } from '@/shared/model/libproject/category.model';

export interface IBook {
  id?: number;
  title?: string;
  author?: string;
  fine_amount?: number;
  publisher?: string;
  quantity?: number;
  category?: ICategory | null;
}

export class Book implements IBook {
  constructor(
    public id?: number,
    public title?: string,
    public author?: string,
    public fine_amount?: number,
    public publisher?: string,
    public quantity?: number,
    public category?: ICategory | null
  ) {}
}
