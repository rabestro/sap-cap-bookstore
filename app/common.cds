using {rabestro.bookshop as bookshop} from '../db/entities';

annotate bookshop.Books with {
    author   @title: 'Author';
    title    @title: 'Title';
    genre    @title: 'Genre';
    price    @title: 'Price';
    descr    @title: 'Description';
    rating   @title: 'Rating';
    currency @UI.Hidden;
    id       @UI.Hidden;
}
