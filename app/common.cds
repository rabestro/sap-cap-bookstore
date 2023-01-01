using {rabestro.bookshop as bookshop} from '../db/index';

annotate bookshop.Books with {
    author   @title: 'Author';
    title    @title: 'Title';
    genre    @title: 'Genre';
    price    @title: 'Price';
    descr    @title: 'Description';
    currency @UI.Hidden;
    id       @UI.Hidden;
}
