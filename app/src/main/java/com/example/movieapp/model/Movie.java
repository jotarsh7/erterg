package com.example.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The Movie class represents a movie with only the properties that are used in the app.
 * It implements Parcelable to allow Movie objects to be passed between Android components.
 */
public class Movie implements Parcelable {

    // Only the fields used in the app are retained.
    private String Title;
    private String Year;
    private String Plot;
    private String Poster;
    private String imdbRating;
    private String imdbID;
    private String Production;

    // Getters for the used fields.
    public String getTitle() { return Title; }
    public String getYear() { return Year; }
    public String getPlot() { return Plot; }
    public String getPoster() { return Poster; }
    public String getImdbRating() { return imdbRating; }
    public String getImdbID() { return imdbID; }
    public String getProduction() { return Production; }

    /**
     * Constructor used for Parcelable implementation.
     * Reads the used properties from the Parcel.
     *
     * A try-catch block is used here to catch any unexpected exceptions during parcel reading.
     *
     * @param in The Parcel containing the movie data.
     */
    protected Movie(Parcel in) {
        try {
            Title = in.readString();
            Year = in.readString();
            Plot = in.readString();
            Poster = in.readString();
            imdbRating = in.readString();
            imdbID = in.readString();
            Production = in.readString();
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally, initialize fields to default values if needed.
            Title = "";
            Year = "";
            Plot = "";
            Poster = "";
            imdbRating = "";
            imdbID = "";
            Production = "";
        }
    }

    /**
     * A Parcelable.Creator that instantiates Movie objects from a Parcel.
     */
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

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes the Movie object properties into a Parcel.
     *
     * A try-catch block is added to handle any unexpected exceptions during the write operation.
     *
     * @param parcel The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        try {
            parcel.writeString(Title);
            parcel.writeString(Year);
            parcel.writeString(Plot);
            parcel.writeString(Poster);
            parcel.writeString(imdbRating);
            parcel.writeString(imdbID);
            parcel.writeString(Production);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Overrides equals() to compare movies based on their unique imdbID.
     *
     * @param o The object to compare with.
     * @return True if both Movie objects have the same imdbID, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;
        return imdbID != null ? imdbID.equals(movie.imdbID) : movie.imdbID == null;
    }

    /**
     * Overrides hashCode() to generate a hash code based on imdbID.
     *
     * @return The hash code for this Movie.
     */
    @Override
    public int hashCode() {
        return imdbID != null ? imdbID.hashCode() : 0;
    }
}
