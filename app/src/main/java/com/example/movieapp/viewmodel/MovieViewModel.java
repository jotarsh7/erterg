package com.example.movieapp.viewmodel;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.movieapp.model.Movie;
import com.example.movieapp.utils.MovieRepository;
import java.util.List;

/**
 * MovieViewModel is responsible for managing and preparing movie data for the UI.
 * It performs movie search operations in a background thread and updates the LiveData object on the main thread.
 */
public class MovieViewModel extends ViewModel {

    // MutableLiveData to hold the list of movies returned from a search.
    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    // Repository that handles the data operations for movie search.
    private MovieRepository repository = new MovieRepository();

    /**
     * Returns a LiveData object that observers can use to get updates on the movie list.
     *
     * @return LiveData containing a list of Movie objects.
     */
    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    /**
     * Initiates a search for movies based on the provided query string.
     * This method runs the network call on a background thread and posts the result to the main thread.
     *
     * @param query The search query (e.g., movie title).
     */
    public void searchMovies(final String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Perform the network call to search for movies.
                    final List<Movie> movieList = repository.searchMovies(query);
                    // Post the search result back to the main thread.
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            movies.setValue(movieList);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    // In case of an error, update the LiveData with null or an empty list as needed.
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            movies.setValue(null);
                        }
                    });
                }
            }
        }).start();
    }
}
