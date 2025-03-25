package com.example.movieapp.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.movieapp.databinding.FragmentDetailsBinding;
import com.example.movieapp.model.Movie;

/**
 * DetailsFragment displays detailed information about a specific movie.
 * It utilizes view binding for interacting with the UI elements and Glide for loading images.
 */
public class DetailsFragment extends Fragment {

    // Key used to pass the Movie object via a Bundle.
    private static final String ARG_MOVIE = "movie";

    // Binding object for accessing the fragment's views.
    private FragmentDetailsBinding binding;

    // The Movie object whose details will be displayed.
    private Movie movie;

    /**
     * Required empty public constructor.
     */
    public DetailsFragment() {
        // Default constructor.
    }

    /**
     * Creates a new instance of DetailsFragment with the specified Movie object.
     *
     * @param movie The Movie object to display.
     * @return A new instance of DetailsFragment.
     */
    public static DetailsFragment newInstance(Movie movie) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        // Place the Movie object in the bundle using the parcelable interface.
        args.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called during the fragment's creation.
     * Retrieves the Movie object from the arguments bundle.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if arguments were provided and retrieve the Movie object.
        if (getArguments() != null) {
            movie = getArguments().getParcelable(ARG_MOVIE);
        }
    }

    /**
     * Inflates the fragment's layout using view binding.
     *
     * @param inflater The LayoutInflater to inflate the layout.
     * @param container The parent view that the fragment's UI should attach to.
     * @param savedInstanceState The saved instance state bundle.
     * @return The root view of the fragment.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout using FragmentDetailsBinding.
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Called immediately after onCreateView.
     * Sets up the UI components with movie details and handles user interactions.
     *
     * @param view The root view returned by onCreateView.
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Display movie details in the UI.
        binding.tvTitle.setText(movie.getTitle());
        binding.tvYear.setText("Year: " + movie.getYear());
        binding.tvRated.setText("Rated: " + movie.getImdbRating());
        binding.tvProduction.setText("Production: " + movie.getProduction());
        binding.tvPlot.setText(movie.getPlot());

        // Load the movie poster image into the ImageView using Glide.
        Glide.with(getContext())
                .load(movie.getPoster())
                .into(binding.ivPoster);

        // Set a click listener for the Back button to navigate to the previous screen.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pop the back stack to return to the previous fragment.
                getParentFragmentManager().popBackStack();
            }
        });
    }

    /**
     * Called when the view created in onCreateView is about to be destroyed.
     * Releases the binding object to prevent memory leaks.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Set binding to null to free up resources.
        binding = null;
    }
}
