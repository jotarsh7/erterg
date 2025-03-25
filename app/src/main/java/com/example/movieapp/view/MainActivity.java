package com.example.movieapp.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.movieapp.databinding.ActivityMainBinding;

/**
 * MainActivity serves as the entry point for the application.
 * It initializes view binding, sets the main content view, and loads the SearchFragment.
 */
public class MainActivity extends AppCompatActivity {

    // View binding instance for accessing views defined in activity_main.xml.
    private ActivityMainBinding binding;

    /**
     * Called when the activity is starting.
     * This method sets up view binding and loads the initial fragment.
     *
     * @param savedInstanceState If the activity is being re-initialized after a previous shutdown,
     *                           this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using view binding.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Set the content view to the root view of the binding.
        setContentView(binding.getRoot());

        // Load the SearchFragment only if the activity is created for the first time.
        // This check prevents reloading the fragment on configuration changes (e.g., screen rotations).
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    // Replace the fragment container with a new instance of SearchFragment.
                    .replace(binding.fragmentContainer.getId(), new SearchFragment())
                    .commit();
        }
    }
}
