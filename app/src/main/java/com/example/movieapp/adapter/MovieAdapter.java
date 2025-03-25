package com.example.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieapp.databinding.ItemMovieBinding;
import com.example.movieapp.model.Movie;

/**
 * MovieAdapter is a RecyclerView adapter that binds Movie data to item views.
 * It extends ListAdapter to efficiently handle list updates with DiffUtil.
 */
public class MovieAdapter extends ListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    /**
     * Interface definition for a callback to be invoked when a movie item is clicked.
     */
    public interface OnItemClickListener {
        /**
         * Called when a movie item has been clicked.
         *
         * @param movie The Movie object that was clicked.
         */
        void onItemClick(Movie movie);
    }

    // Listener to handle click events on movie items.
    private final OnItemClickListener listener;

    /**
     * Constructor for MovieAdapter.
     *
     * @param listener A listener for handling item click events.
     */
    public MovieAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    /**
     * A DiffUtil.ItemCallback implementation for calculating the differences between two non-null Movie items.
     * This is used to improve performance when updating the list.
     */
    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                /**
                 * Called to check whether two Movie objects represent the same item.
                 *
                 * @param oldItem The old Movie item.
                 * @param newItem The new Movie item.
                 * @return True if both items have the same unique ID.
                 */
                @Override
                public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                    // Compare unique IDs (e.g., IMDb ID) to determine if items are the same.
                    return oldItem.getImdbID().equals(newItem.getImdbID());
                }

                /**
                 * Called to check whether the contents of two Movie objects are the same.
                 *
                 * @param oldItem The old Movie item.
                 * @param newItem The new Movie item.
                 * @return True if both items' data are equal.
                 */
                @Override
                public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                    // With equals() overridden in Movie, this works as expected.
                    return oldItem.equals(newItem);
                }
            };

    /**
     * Called when RecyclerView needs a new {@link MovieViewHolder} to represent an item.
     *
     * @param parent   The parent ViewGroup into which the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new MovieViewHolder that holds a binding to the item layout.
     */
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            // Inflate the layout for individual movie items using view binding.
            ItemMovieBinding binding = ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
            );
            return new MovieViewHolder(binding);
        } catch (Exception e) {
            e.printStackTrace();
            // In case of an exception, rethrow as a RuntimeException.
            throw new RuntimeException("Error inflating movie item layout", e);
        }
    }

    /**
     * Called by RecyclerView to display data at the specified position.
     *
     * @param holder   The MovieViewHolder which should be updated.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        try {
            // Retrieve the current Movie item and bind it to the holder.
            Movie movie = getItem(position);
            holder.bind(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ViewHolder class for Movie items.
     * It holds references to the layout views for each movie item.
     */
    class MovieViewHolder extends RecyclerView.ViewHolder {
        // Binding for the movie item layout.
        private final ItemMovieBinding binding;

        /**
         * Constructor for MovieViewHolder.
         *
         * @param binding The binding object for the movie item layout.
         */
        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Binds a Movie object to the UI elements in the item view.
         *
         * @param movie The Movie object whose data is to be displayed.
         */
        public void bind(final Movie movie) {
            try {
                // Set the movie title.
                binding.tvTitle.setText(movie.getTitle());
                // Set the movie release year.
                binding.tvYear.setText(movie.getYear());
                // Set the movie rating with a prefix.
                binding.tvRated.setText("Rated: " + movie.getImdbRating());
                // Set the movie production company with a prefix.
                binding.tvProduction.setText("Production: " + movie.getProduction());

                // Set a click listener on the root view that notifies the listener when a movie is clicked.
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            listener.onItemClick(movie);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
