package exampls.com.movieappplus.Model;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class Review {
    String reviewName, review;

    public Review(String reviewName, String review) {
        this.reviewName = reviewName;
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public String getReviewName() {
        int i = 1;
        return reviewName;
    }
}
