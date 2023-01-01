namespace rabestro.bookshop;

using {Currency} from '@sap/cds/common';

entity Books {
    key id       : Int64;
        title    : String(100);
        descr    : String(5000);
        author   : String(100);
        genre    : String(100);
        price    : Decimal(9, 2);
        currency : Currency;
}
