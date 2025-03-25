package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * MovieSearchResponse represents the response from a movie search API call.
 * It includes a list of movie summaries, the total number of results,
 * and a response flag indicating if the API call was successful.
 */
public class MovieSearchResponse {

    /**
     * The list of movie summaries returned from the API.
     */
    @SerializedName("Search")
    private List<MovieSummary> Search;

    /**
     * The total number of results as returned by the API.
     */
    @SerializedName("totalResults")
    private String totalResults;

    /**
     * The response flag indicating success ("True") or failure ("False").
     */
    @SerializedName("Response")
    private String Response;

    /**
     * Returns the list of movie summaries.
     *
     * @return A list of MovieSummary objects.
     */
    public List<MovieSummary> getSearch() {
        return Search;
    }

    /**
     * Returns the total number of results.
     *
     * @return The total number of results as a String.
     */
    public String getTotalResults() {
        return totalResults;
    }

    /**
     * Returns the API response flag.
     *
     * @return "True" if the API call was successful, "False" otherwise.
     */
    public String getResponse() {
        return Response;
    }
}