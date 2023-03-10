package rabestro.bookstore.handlers;

import cds.gen.catalogservice.AddReviewContext;
import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.Reviews;
import cds.gen.catalogservice.Reviews_;
import cds.gen.rabestro.bookshop.Books;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import org.springframework.stereotype.Component;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {
    private final PersistenceService db;
    private final CqnAnalyzer analyzer;

    public CatalogServiceHandler(PersistenceService db, CdsModel model) {
        this.analyzer = CqnAnalyzer.create(model);
        this.db = db;
    }

    @On(event = AddReviewContext.CDS_NAME)
    public void addReview(AddReviewContext context) {
        var savedReview = this.new BookReview(context).save();
        context.setResult(savedReview);
    }

    private class BookReview {
        private final Reviews review;

        BookReview(AddReviewContext context) {
            review = Reviews.create();
            review.setBookId(bookId(context));
            review.setTitle(context.getTitle());
            review.setText(context.getText());
            review.setRating(context.getRating());
        }

        private String bookId(AddReviewContext context) {
            return (String) CatalogServiceHandler.this.analyzer
                    .analyze(context.getCqn())
                    .targetKeys()
                    .get(Books.ID);
        }

        Reviews save() {
            var sql = Insert.into(Reviews_.CDS_NAME).entry(review);
            return db.run(sql)
                    .single(Reviews.class);
        }
    }
}
