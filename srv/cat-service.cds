using {rabestro.bookshop as bookshop} from '../db/books';

service CatalogService {
    entity Books as projection on bookshop.Books;
}

