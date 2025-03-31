package com.example.week_11.Container;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.week_11.Interfaces.SoccerEntity;

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

    // Add item to container
    public void addItem(T item) {
        soccerEntities.add(item);
    }

    // Add all items from List to container
    public void addAll(List<T> soccerEntities){
        soccerEntities.forEach(this::addItem);
    }

    // Remove item from container
    public boolean removeItem(T item) {
        return soccerEntities.remove(item);
    }

    // Get Item at specific index
    public T getItem(int index) {
        if (index >= 0 && index < soccerEntities.size()) {
            return soccerEntities.get(index);
        }
        return null;
    }

    // Get all items from container
    public List<T> getAllItems() {
        return new ArrayList<>(soccerEntities);
    }

    // Get size of container
    public int size() {
        return soccerEntities.size();
    }

    // Filter items based on predicate
    public SoccerEntityContainer<T> filter(Predicate<T> predicate, String desiredText) {
        SoccerEntityContainer<T> filteredContainer = new SoccerEntityContainer<>();

        // Using stream and lambda to filter items
        List<T> filteredItems = soccerEntities.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        // Add filtered items to the new container
        filteredItems.forEach(filteredContainer::addItem);
        if(desiredText != null && !desiredText.isEmpty()){
            Log.d("DSA", desiredText);
            for (T t : filteredItems){
                if(t instanceof SoccerEntity){
                    SoccerEntity tSoccerEntity = (SoccerEntity) t;
                    if(!tSoccerEntity.getName().toLowerCase().contains(desiredText.toLowerCase())){
                        filteredContainer.removeItem(t);
                    }

                }
            }
        }
        return filteredContainer;
    }



    // Process items
    public void processItems(java.util.function.Consumer<T> processor) {
        soccerEntities.forEach(processor);
    }

    // Implement iterator
    @NonNull
    @Override
    public Iterator<T> iterator() {
        return soccerEntities.iterator();
    }

    // Implement Custom iterator
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

    // Get new iterator
    public InventoryIterator getCustomIterator() {
        return new InventoryIterator();
    }
}

