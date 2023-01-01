namespace rabestro.bookshop;

entity Books {
    key id : Int64;
    title : String(100);
    descr : String(5000);
    author : String(100);
    genre : String(100);
}
