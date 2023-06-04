package com.example.designpatterninkotlinjava.behavioral.iterator.sample;

import androidx.annotation.NonNull;


public class Inventory implements Iterable {
    private Item[] items;

    public Inventory(Item... items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    @NonNull
    @Override
    public StockIterator iterator() {
        return new StockIterator(this);
    }
}
