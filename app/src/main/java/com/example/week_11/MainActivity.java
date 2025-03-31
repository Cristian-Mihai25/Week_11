package com.example.week_11;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week_11.Adapter.SoccerEntityAdapter;
import com.example.week_11.Container.SoccerEntityContainer;
import com.example.week_11.Entities.Match;
import com.example.week_11.Entities.Player;
import com.example.week_11.Entities.Team;
import com.example.week_11.Interfaces.SoccerEntity;
import com.example.week_11.Iterators.TeamIterator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    // UI Components
    private RecyclerView recyclerSoccerEntities;
    private TextView tvEmptyView;
    private SearchView searchView;
    private SoccerEntityAdapter adapter;

    DataProvider<Team> teamProvider;
    DataProvider<Player> playerProvider;
    DataProvider<Match> matchProvider;
    String textToSearch;

    // Generic container to hold inventory items
    private SoccerEntityContainer<SoccerEntity> soccerEntityContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initViews();

        // Set up the container and populate with sample data
        soccerEntityContainer = new SoccerEntityContainer<>();
        List<SoccerEntity> allEntities = new ArrayList<>();

// Create DataProviders for each entity type
        teamProvider = new DataProvider<>(DataProvider.createSampleTeams());
        playerProvider = new DataProvider<>(DataProvider.createSamplePlayers());
        matchProvider = new DataProvider<>(DataProvider.createSampleMatches());

// Add all entities to a single list
        allEntities.addAll(teamProvider.getAll());
        allEntities.addAll(playerProvider.getAll());
        allEntities.addAll(matchProvider.getAll());

        soccerEntityContainer.addAll(allEntities);


        // Set up the RecyclerView and adapter
        setupRecyclerView();

        // Set up button click listeners
        setupButtonListeners();


    }

    /**
     * Initialize UI components
     */
    private void initViews() {
        recyclerSoccerEntities = findViewById(R.id.recycler_inventory);
        tvEmptyView = findViewById(R.id.tv_empty_view);
        searchView = findViewById(R.id.search_view); // Add this line

        // Initialize search text
        textToSearch = "";
    }

    // Add search functionality in setupRecyclerView() after setting up the RecyclerView
    private void setupRecyclerView() {
        recyclerSoccerEntities.setLayoutManager(new LinearLayoutManager(this));

        // Create the adapter with an empty list initially
        adapter = new SoccerEntityAdapter(
                this,
                new ArrayList<>()
        );

        recyclerSoccerEntities.setAdapter(adapter);

        // Update adapter with all items
        updateAdapterItems(soccerEntityContainer.getAllItems());

        // Set up search functionality
        setupSearch();
    }

    // Add a new method to set up the search functionality
    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterEntitiesByName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterEntitiesByName(newText);
                return true;
            }
        });
    }

    // Add a method to filter entities by name
    private void filterEntitiesByName(String query) {
        textToSearch = query.trim();

        if (textToSearch.isEmpty()) {
            // If search is empty, show all items
            updateAdapterItems(soccerEntityContainer.getAllItems());
        } else {
            // Filter items by name containing the search query (case-insensitive)
            Predicate<SoccerEntity> nameFilter = item ->
                    item.getName().toLowerCase().contains(textToSearch.toLowerCase());

            SoccerEntityContainer<SoccerEntity> filtered = soccerEntityContainer.filter(nameFilter, textToSearch);
            updateAdapterItems(filtered.getAllItems());
        }
    }



    /**
     * Set up click listeners for filter buttons
     * Demonstrates use of lambda functions and generics
     */
    private void setupButtonListeners() {
        // Show all items
        Button btnShowAll = findViewById(R.id.btn_show_all);
        btnShowAll.setOnClickListener(v -> {
            // Lambda function for click handler
            updateAdapterItems(soccerEntityContainer.getAllItems());
            showToast("Showing all items");
        });

        // Filter for Teams only
        Button btnFilterBooks = findViewById(R.id.btn_filter_Teams);
        btnFilterBooks.setOnClickListener(v -> {

            // Using lambda predicate with generics
            Predicate<SoccerEntity> bookFilter = item -> item instanceof Team;
            SoccerEntityContainer<SoccerEntity> filtered = soccerEntityContainer.filter(bookFilter, textToSearch);

            updateAdapterItems(filtered.getAllItems());
            showToast("Filtered: Teams only");
        });

        // Filter for Players only
        Button btnFilterElectronics = findViewById(R.id.btn_filter_Players);
        btnFilterElectronics.setOnClickListener(v -> {
            // Lambda predicate example
            updateAdapterItems(
                    soccerEntityContainer.filter(item -> item instanceof Player, textToSearch).getAllItems()
            );
            showToast("Filtered: Players only");
        });

        // Filter for Matches only
        Button btnFilterTools = findViewById(R.id.btn_filter_Matches);
        btnFilterTools.setOnClickListener(v -> {
            updateAdapterItems(
                    soccerEntityContainer.filter(item -> item instanceof Match, textToSearch).getAllItems()
            );
            showToast("Filtered: Matches only");
        });


        // * Filter from oldest (using lambda comparator)
        Button btnSortOldest = findViewById(R.id.btn_from_oldest);
        btnSortOldest.setOnClickListener(v -> {
            // Using stream with lambda comparator
            List<SoccerEntity> sortedItems = soccerEntityContainer.getAllItems().stream()
                    .sorted(Comparator.comparingDouble(SoccerEntity::getBirthDate))
                    .collect(Collectors.toList());

            updateAdapterItems(sortedItems);
            showToast("Sorted by age (ascending)");
        });

        //Order alphabetically by name
        Button btnSortAlphabetically = findViewById(R.id.btn_alphabetically);
        btnSortAlphabetically.setOnClickListener(v -> {
            // Using stream with lambda comparator
            List<SoccerEntity> sortedItems = soccerEntityContainer.getAllItems().stream()
                    .sorted(Comparator.comparing(SoccerEntity::getName))
                    .collect(Collectors.toList());

            updateAdapterItems(sortedItems);
            showToast("Sorted alphabetically (ascending)");
        });


        // Iterator demonstration - using custom iterator


    }


    private void updateAdapterItems(List<SoccerEntity> soccerEntities) {
        adapter.updateItems(soccerEntities);

        // Show/hide empty view
        if (soccerEntities.isEmpty()) {
            tvEmptyView.setVisibility(android.view.View.VISIBLE);
            recyclerSoccerEntities.setVisibility(android.view.View.GONE);
        } else {
            tvEmptyView.setVisibility(android.view.View.GONE);
            recyclerSoccerEntities.setVisibility(android.view.View.VISIBLE);
        }
    }




    //Iterators




    // ShowDetails
    private void showItemDetails(SoccerEntity item) {
        // Simple toast to show item details
        showToast("Selected: " + item.getName());
    }

    // Helper message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}