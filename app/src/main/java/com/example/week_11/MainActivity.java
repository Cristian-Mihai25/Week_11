package com.example.week_11;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity {

    // UI Components
    private RecyclerView recyclerSoccerEntities;
    private TextView tvEmptyView;
    private SearchView searchView;
    private SoccerEntityAdapter adapter;

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
        DataProvider<Team> teamProvider = new DataProvider<>(DataProvider.createSampleTeams());
        DataProvider<Player> playerProvider = new DataProvider<>(DataProvider.createSamplePlayers());
        DataProvider<Match> matchProvider = new DataProvider<>(DataProvider.createSampleMatches());

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
    }

    /**
     * Set up the generic container and populate with sample data
     * Demonstrates using a generic container
     *
    private void setupInventoryContainer() {
        // Initialize the generic container
        soccerEntityContainer = new InventoryContainer<>();

        // Add sample books
        inventoryContainer.addItem(new Book("Java Programming", 59.99, 10,
                "John Smith", "978-0134685991", 850));
        inventoryContainer.addItem(new Book("Android Development", 49.99, 15,
                "Alice Johnson", "978-1449363444", 652));
        inventoryContainer.addItem(new Book("Design Patterns", 39.99, 8,
                "Gang of Four", "978-0201633610", 416));

        // Add sample electronics
        inventoryContainer.addItem(new Electronic("Laptop", 899.99, 5,
                "Dell", "XPS 13", 24));
        inventoryContainer.addItem(new Electronic("Smartphone", 699.99, 12,
                "Samsung", "Galaxy S21", 12));
        inventoryContainer.addItem(new Electronic("Tablet", 349.99, 8,
                "Apple", "iPad Air", 12));

        // Add sample tools
        inventoryContainer.addItem(new Tool("Hammer", 19.99, 20,
                "Stanley", false, "Hardware"));
        inventoryContainer.addItem(new Tool("Drill", 89.99, 7,
                "DeWalt", true, "Power Tools"));
        inventoryContainer.addItem(new Tool("Saw", 59.99, 5,
                "Makita", true, "Power Tools"));
    }

    /**
     * Set up the RecyclerView and adapter
     */
    private void setupRecyclerView() {
        recyclerSoccerEntities.setLayoutManager(new LinearLayoutManager(this));

        // Create the adapter with an empty list initially
        adapter = new SoccerEntityAdapter(
                this,
                new ArrayList<>(),
                (soccerEntity, position) -> {
                    // Lambda function for item click
                    showItemDetails(soccerEntity);
                }
        );

        recyclerSoccerEntities.setAdapter(adapter);

        // Update adapter with all items
        updateAdapterItems(soccerEntityContainer.getAllItems());
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

        // Filter for books only using lambda predicate
        Button btnFilterBooks = findViewById(R.id.btn_filter_Teams);
        btnFilterBooks.setOnClickListener(v -> {

            // Using lambda predicate with generics
            Predicate<SoccerEntity> bookFilter = item -> item instanceof Team;
            SoccerEntityContainer<SoccerEntity> filtered = soccerEntityContainer.filter(bookFilter, textToSearch);

            updateAdapterItems(filtered.getAllItems());
            showToast("Filtered: Books only");
        });

        // Filter for electronics only
        Button btnFilterElectronics = findViewById(R.id.btn_filter_Players);
        btnFilterElectronics.setOnClickListener(v -> {
            // Lambda predicate example
            updateAdapterItems(
                    soccerEntityContainer.filter(item -> item instanceof Player, textToSearch).getAllItems()
            );
            showToast("Filtered: Electronics only");
        });

        // Filter for tools only
        Button btnFilterTools = findViewById(R.id.btn_filter_Matches);
        btnFilterTools.setOnClickListener(v -> {
            updateAdapterItems(
                    soccerEntityContainer.filter(item -> item instanceof Match, textToSearch).getAllItems()
            );
            showToast("Filtered: Tools only");
        });

        // Filter for expensive items (price > 100)
        /*
        Button btnFilterExpensive = findViewById(R.id.btn_filter_expensive);
        btnFilterExpensive.setOnClickListener(v -> {
            updateAdapterItems(
                    soccerEntityContainer.filter(item -> item.getPrice() > 100).getAllItems()
            );
            showToast("Filtered: Expensive items (>$100)");
        });

        // Sort by price (using lambda comparator)
        /*Button btnSortPrice = findViewById(R.id.btn_sort_price);
        btnSortPrice.setOnClickListener(v -> {
            // Using stream with lambda comparator
            List<SoccerEntity> sortedItems = soccerEntityContainer.getAllItems().stream()
                    .sorted(Comparator.comparingDouble(SoccerEntity::getPrice))
                    .collect(Collectors.toList());

            updateAdapterItems(sortedItems);
            showToast("Sorted by price (ascending)");
        });
        */
        // Iterator demonstration - using for-each (which uses the Iterator interface)
        Button btnForeachIterator = findViewById(R.id.btn_foreach_iterator);
        btnForeachIterator.setOnClickListener(v -> {
            demonstrateForEachIterator();
        });

        // Iterator demonstration - using custom iterator
        Button btnCustomIterator = findViewById(R.id.btn_custom_iterator);
        btnCustomIterator.setOnClickListener(v -> {
            demonstrateCustomIterator();
        });
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

    /**
     * Demonstrate using the for-each loop which uses the Iterator interface
     * Shows how implementing Iterable allows for enhanced for loop
     */
    private void demonstrateForEachIterator() {
        StringBuilder result = new StringBuilder("Using for-each loop (Iterator):\n");

        // Using the for-each loop which uses the Iterator interface
        for (SoccerEntity item : soccerEntityContainer) {
            result.append(" - ").append(item.getName()).append("\n");
        }

        showToast("Check logs for iterator demo results");
        System.out.println(result.toString());
    }

    /**
     * Demonstrate using the custom iterator directly
     * Shows explicit use of an Iterator
     */
    private void demonstrateCustomIterator() {
        StringBuilder result = new StringBuilder("Using custom iterator:\n");

        // Get custom iterator
        SoccerEntityContainer<SoccerEntity>.InventoryIterator iterator =
                soccerEntityContainer.getCustomIterator();

        // Manually use the iterator
        while (iterator.hasNext()) {
            SoccerEntity item = iterator.next();
            result.append(" - ").append(item.getName()).append("\n");
        }

        showToast("Check logs for custom iterator demo results");
        System.out.println(result.toString());
    }

    /**
     * Show details for an item
     * @param item the item to show details for
     */
    private void showItemDetails(SoccerEntity item) {
        // Simple toast to show item details
        showToast("Selected: " + item.getName());
    }

    /**
     * Helper method to show toast
     * @param message the message to show
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}