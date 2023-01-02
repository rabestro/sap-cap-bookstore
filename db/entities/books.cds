namespace rabestro.bookshop;

using {rabestro.bookshop as bookshop} from './index';

using {
    Currency,
    cuid
} from '@sap/cds/common';

entity Books : cuid {
    isbn     : Int64;
    title    : bookshop.Name @mandatory;
    descr    : bookshop.Text;
    author   : bookshop.Name @mandatory;
    genre    : bookshop.Name;
    rating   : Decimal(2, 1) @assert.range: [
        0.0,
        5.0
    ];
    price    : Decimal(9, 2) @assert.range: [
        0.0,
        9999999.99
    ];
    currency : Currency;
    reviews  : Association to many bookshop.Reviews
                   on reviews.book = $self;
}
