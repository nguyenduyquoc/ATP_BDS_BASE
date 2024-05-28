package com.anthinhphatjsc.ezisolutions.core;

public class SingletonClass {

    private static SingletonClass INSTANCE;

    public static SingletonClass getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonClass();
        }

        return INSTANCE;
    }
}
