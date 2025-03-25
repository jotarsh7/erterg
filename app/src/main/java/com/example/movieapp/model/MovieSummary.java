package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * The MovieSummary class represents a brief summary of a movie as returned by the movie API.
 * It includes key details such as the title, release year, IMDb ID, type, and poster URL.
 */
public class MovieSummary {

    /**
     * The title of the movie.
     */
    @SerializedName("Title")
    private String Title;

    /**
     * The year the movie was released.
     */
    @SerializedName("Year")
    private String Year;

    /**
     * The unique IMDb identifier for the movie.
     */
    @SerializedName("imdbID")
    private String imdbID;

    /**
     * The type of media (e.g., movie, series).
     */
    @SerializedName("Type")
    private String Type;

    /**
     * The URL of the movie poster image.
     */
    @SerializedName("Poster")
    private String Poster;

    /**
     * Returns the movie title.
     *
     * @return The title of the movie.
     */
    public String getTitle() { return Title; }

    /**
     * Returns the release year of the movie.
     *
     * @return The release year as a String.
     */
    public String getYear() { return Year; }

    /**
     * Returns the unique IMDb identifier for the movie.
     *
     * @return The IMDb ID.
     */
    public String getImdbID() { return imdbID; }

    /**
     * Returns the type of media.
     *
     * @return The type (e.g., "movie" or "series").
     */
    public String getType() { return Type; }

    /**
     * Returns the URL of the movie poster.
     *
     * @return The poster URL.
     */
    public String getPoster() { return Poster; }
}
