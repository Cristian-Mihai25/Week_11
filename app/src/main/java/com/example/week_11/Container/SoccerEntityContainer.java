package com.example.week_11.Container;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Generic container class for inventory items.
 * Demonstrates generics and implements Iterable interface.
 *
 * @param <T> the type of elements in this container
 */
public class SoccerEntityContainer<T> implements Iterable<T> {

    private final List<T> soccerEntities;

    public SoccerEntityContainer() {
        this.soccerEntities = new ArrayList<>();
    }

    /**
     * Add an item to the container
     *
     * @param item the item to add
     */
    public void addItem(T item) {
        soccerEntities.add(item);
    }

    public void addAll(List<T> soccerEntities){
        for (T entity : soccerEntities){
            addItem(entity);

        }
    }

    /**
     * Remove an item from the container
     * @param item the item to remove
     * @return true if successful
     */
    public boolean removeItem(T item) {
        return soccerEntities.remove(item);
    }

    /**
     * Get item at specific index
     * @param index the index
     * @return the item at the index
     */
    public T getItem(int index) {
        if (index >= 0 && index < soccerEntities.size()) {
            return soccerEntities.get(index);
        }
        return null;
    }

    /**
     * Get all items in the container
     * @return list of all items
     */
    public List<T> getAllItems() {
        return new ArrayList<>(soccerEntities);
    }

    /**
     * Get the number of items in the container
     * @return size of the container
     */
    public int size() {
        return soccerEntities.size();
    }

    /**
     * Filter items based on a predicate (lambda function)
     * Demonstrates using lambda functions with generics
     *
     * @param predicate the filter condition
     * @return new container with filtered items
     */
    public SoccerEntityContainer<T> filter(Predicate<T> predicate, String desiredText) {
        SoccerEntityContainer<T> filteredContainer = new SoccerEntityContainer<>();

        // Using stream and lambda to filter items
        List<T> filteredItems = soccerEntities.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        // Add filtered items to the new container
        filteredItems.forEach(filteredContainer::addItem);

        return filteredContainer;
    }



    /**
     * Process each item using a lambda function
     * @param processor the lambda function to apply to each item
     */
    public void processItems(java.util.function.Consumer<T> processor) {
        soccerEntities.forEach(processor);
    }

    /**
     * Implementation of the Iterator method from Iterable interface
     * Allows for using this container in for-each loops
     */
    @NonNull
    @Override
    public Iterator<T> iterator() {
        return soccerEntities.iterator();
    }

    /**
     * Custom iterator implementation example
     * (Using ArrayList's iterator in this case, but demonstrates the concept)
     */
    public class InventoryIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < soccerEntities.size();
        }

        @Override
        public T next() {
            return soccerEntities.get(currentIndex++);
        }
    }

    /**
     * Get a custom iterator (optional method showing custom iterator creation)
     * @return custom iterator for this container
     */
    public InventoryIterator getCustomIterator() {
        return new InventoryIterator();
    }
}
