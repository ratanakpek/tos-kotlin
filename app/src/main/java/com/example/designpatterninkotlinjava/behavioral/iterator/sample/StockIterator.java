package com.example.designpatterninkotlinjava.behavioral.iterator.sample;

import java.util.Iterator;

public class StockIterator implements Iterator {
    private int index;
    private Inventory inventory;

    public StockIterator(Inventory inventory) {
        this.inventory = inventory;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        Item[] items = inventory.getItems();
        if (index < items.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Item next() {
        Item[] items = inventory.getItems();
        if (hasNext()) {
            Item item = items[index++];
            if (item.getQuantity() > 0) {
                return item;
            } else {
                return next();
            }
        } else {
            return null;
        }
    }
}
