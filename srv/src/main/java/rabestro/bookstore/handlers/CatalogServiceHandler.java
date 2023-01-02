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
        db.run(Insert
                .into(Reviews_.CDS_NAME)
                .entry(review(context)));
    }

    private Reviews review(AddReviewContext context) {
        var review = Reviews.create();
        review.setBookId(bookId(context));
        review.setTitle(context.getTitle());
        review.setText(context.getText());
        review.setRating(context.getRating());
        return review;
    }

    private String bookId(AddReviewContext context) {
        return (String) analyzer
                .analyze(context.getCqn())
                .targetKeys()
                .get(Books.ID);
    }
}
