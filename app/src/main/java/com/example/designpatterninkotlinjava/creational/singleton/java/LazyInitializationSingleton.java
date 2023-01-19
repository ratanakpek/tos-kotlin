package com.example.designpatterninkotlinjava.creational.singleton.java;

public class LazyInitializationSingleton {

    private static volatile LazyInitializationSingleton instance = null;

    private LazyInitializationSingleton() {
    }

    public static LazyInitializationSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyInitializationSingleton.class) {
                if (instance == null) {
                    instance = new LazyInitializationSingleton();
                }
            }
        }
        return instance;
    }

}
