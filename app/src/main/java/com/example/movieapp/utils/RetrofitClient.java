package com.example.movieapp.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitClient is a singleton class that provides an instance of OMDBApiService.
 * It is responsible for creating and configuring the Retrofit instance to communicate with the OMDB API.
 */
public class RetrofitClient {

    // Base URL for the OMDB API.
    private static final String BASE_URL = "https://www.omdbapi.com/";

    // Singleton Retrofit instance.
    private static Retrofit retrofit = null;

    /**
     * Returns an instance of OMDBApiService for making network calls.
     * If the Retrofit instance is not yet created, it initializes it with the base URL and Gson converter.
     *
     * A try-catch block is added to safely handle any exceptions during the creation of the Retrofit instance.
     *
     * @return An instance of OMDBApiService if the Retrofit instance is successfully created, or null otherwise.
     */
    public static OMDBApiService getApiService() {
        if (retrofit == null) {
            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
                // Optionally, handle the error further or rethrow as a runtime exception.
                return null;
            }
        }
        return retrofit.create(OMDBApiService.class);
    }
}
