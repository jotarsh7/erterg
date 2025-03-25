package com.example.movieapp.utils;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * OMDBApiService defines the endpoints for interacting with the OMDB (Open Movie Database) API.
 */
public interface OMDBApiService {

    /**
     * Searches for movies based on a query string.
     */
    @GET("/")
    Call<MovieSearchResponse> searchMovies(
            @Query("apikey") String apiKey,
            @Query("s") String query
    );

    /**
     * Retrieves detailed information for a specific movie by its IMDb ID.
     */
    @GET("/")
    Call<Movie> getMovieDetails(
            @Query("apikey") String apiKey,
            @Query("i") String imdbID,
            @Query("plot") String plot
    );
}
