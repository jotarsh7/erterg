package com.example.movieapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.movieapp.adapter.MovieAdapter;
import com.example.movieapp.databinding.FragmentSearchBinding;
import com.example.movieapp.model.Movie;
import com.example.movieapp.viewmodel.MovieViewModel;
import java.util.List;

/**
 * SearchFragment is responsible for displaying a search UI where users can query movies.
 * It uses a RecyclerView to list movies, observes changes from the MovieViewModel,
 * and navigates to the DetailsFragment when a movie is selected.
 */
public class SearchFragment extends Fragment {

    // View binding instance for accessing the fragment's views.
    private FragmentSearchBinding binding;

    // ViewModel for managing movie data and handling search logic.
    private MovieViewModel movieViewModel;

    // Adapter for binding movie data to the RecyclerView.
    private MovieAdapter adapter;

    /**
     * Required empty public constructor.
     */
    public SearchFragment() {
        // Default constructor
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate views.
     * @param container The parent view that the fragment's UI should attach to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The root View for the fragment's UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout using View Binding.
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Called immediately after onCreateView() has returned.
     * Sets up the RecyclerView, attaches event listeners, and observes the ViewModel's LiveData.
     *
     * @param view The View returned by onCreateView().
     * @param savedInstanceState If non-null, the fragment is being re-constructed from a previous saved state.
     */
    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        // Initialize the ViewModel for this fragment.
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Set up the RecyclerView with a custom adapter.
        adapter = new MovieAdapter(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                // When a movie item is clicked, navigate to the DetailsFragment.
                DetailsFragment detailsFragment = DetailsFragment.newInstance(movie);
                getParentFragmentManager().beginTransaction()
                        // Replace the current fragment container with the DetailsFragment.
                        .replace(((ViewGroup) view.getParent()).getId(), detailsFragment)
                        // Add the transaction to the back stack to allow navigation back.
                        .addToBackStack(null)
                        .commit();
            }
        });
        // Set a linear layout manager for the RecyclerView.
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Assign the adapter to the RecyclerView.
        binding.recyclerView.setAdapter(adapter);

        // Set up the Search button click listener.
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the query from the search input field.
                String query = binding.searchEditText.getText().toString().trim();
                // Only trigger a search if the query is not empty.
                if (!query.isEmpty()) {
                    movieViewModel.searchMovies(query);
                }
            }
        });

        // Observe the LiveData holding the list of movies from the ViewModel.
        movieViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                // Update the adapter's data when movie list changes.
                adapter.submitList(movies);
            }
        });
    }

    /**
     * Called when the view created in onCreateView is about to be destroyed.
     * Clean up resources to avoid memory leaks.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear the binding reference.
        binding = null;
    }
}
