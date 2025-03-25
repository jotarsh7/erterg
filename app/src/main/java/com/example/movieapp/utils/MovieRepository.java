package com.example.movieapp.utils;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieSearchResponse;
import com.example.movieapp.model.MovieSummary;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

/**
 * MovieRepository is responsible for fetching movie data from the OMDB API.
 * It uses Retrofit to make network requests to search for movies and to retrieve detailed movie information.
 */
public class MovieRepository {

    // API key for accessing the OMDB API.
    private final String apiKey = "9ebbef8";

    // Retrofit API service for making network requests.
    private OMDBApiService apiService = RetrofitClient.getApiService();

    /**
     * Searches for movies matching the provided query and retrieves detailed movie information for each result.
     *
     * Try-catch blocks are used to handle exceptions during network calls.
     *
     * @param query The search query (e.g., movie title).
     * @return A list of Movie objects with detailed information.
     */
    public List<Movie> searchMovies(String query) {
        // List to store detailed movie information.
        List<Movie> detailedMovies = new ArrayList<>();
        try {
            // Make a synchronous call to search for movies.
            Call<MovieSearchResponse> call = apiService.searchMovies(apiKey, query);
            Response<MovieSearchResponse> response = call.execute();

            // Check if the response is successful and the body is not null.
            if (response.isSuccessful() && response.body() != null) {
                List<MovieSummary> summaries = response.body().getSearch();

                if (summaries != null) {
                    // Iterate over each movie summary and fetch detailed information.
                    for (MovieSummary summary : summaries) {
                        try {
                            // Make a synchronous call to get detailed movie information.
                            Call<Movie> detailCall = apiService.getMovieDetails(apiKey, summary.getImdbID(), "short");
                            Response<Movie> detailResponse = detailCall.execute();
                            // If the detailed response is successful, add it to the list.
                            if (detailResponse.isSuccessful() && detailResponse.body() != null) {
                                detailedMovies.add(detailResponse.body());
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // Return the list of detailed movies (empty if any error occurs).
        return detailedMovies;
    }
}
