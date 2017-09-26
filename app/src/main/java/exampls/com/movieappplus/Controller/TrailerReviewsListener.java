package exampls.com.movieappplus.Controller;

/**
 *  triggered when trailers and reviews adapter finished
 */


public interface TrailerReviewsListener {

    void trailerGotListener(MyTrailersAdapter trailersAdapter);

    void reviewsGotListener(MyReviewAdapter reviewAdapter);

}
