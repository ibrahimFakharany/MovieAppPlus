package exampls.com.movieappplus.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class Movie implements Parcelable{
    String posterImage;
    String realeseDate;
    String title;
    double Vote_Average;
    String overview;
    int id;

    public Movie() {
    }

    public Movie(String posterImage, String title, int id) {
        this.posterImage = posterImage;
        this.title = title;
        this.id = id;
    }

    public Movie(int id, String posterImage, String title, String realeseDate, double vote_Average, String overview) {
        this.id = id;
        this.posterImage = posterImage;
        this.title = title;
        this.realeseDate = realeseDate;
        Vote_Average = vote_Average;
        this.overview = overview;
    }

    protected Movie(Parcel in) {
        posterImage = in.readString();
        realeseDate = in.readString();
        title = in.readString();
        Vote_Average = in.readDouble();
        overview = in.readString();
        id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPosterImage() {
        return posterImage;
    }

    public String getRealeseDate() {
        return realeseDate;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_Average() {
        return Vote_Average;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterImage);
        dest.writeString(realeseDate);
        dest.writeString(title);
        dest.writeDouble(Vote_Average);
        dest.writeString(overview);
        dest.writeInt(id);
    }
}
