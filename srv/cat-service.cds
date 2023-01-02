using {rabestro.bookshop as bookshop} from '../db/books';

@path: 'browse'
service CatalogService {
    entity Books   as projection on bookshop.Books actions {
        action addReview(rating : Int16, title : String, text : String) returns Reviews;
    };

    entity Reviews as projection on bookshop.Reviews;
}
