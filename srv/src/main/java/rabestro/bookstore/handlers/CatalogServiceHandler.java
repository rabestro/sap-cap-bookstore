package rabestro.bookstore.handlers;

import cds.gen.catalogservice.AddReviewContext;
import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.Reviews;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

    @On(event = AddReviewContext.CDS_NAME)
    public void addReview(AddReviewContext context) {
        var review = Reviews.create();
        review.setTitle(context.getTitle());
        review.setText(context.getText());
        review.setRating(context.getRating());
    }
}
