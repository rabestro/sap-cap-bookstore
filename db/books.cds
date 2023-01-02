namespace rabestro.bookshop;

using {Currency} from '@sap/cds/common';

entity Books {
    key id       : Int64;
        title    : String(100)   @mandatory;
        descr    : String(5000);
        author   : String(100)   @mandatory;
        genre    : String(100);
        rating   : Decimal(2, 1) @assert.range: [
            0.0,
            5.0
        ];
        price    : Decimal(9, 2) @assert.range: [
            0.0,
            9999999.99
        ];
        currency : Currency;
}
