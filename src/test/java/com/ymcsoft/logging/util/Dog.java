package com.ymcsoft.logging.util;

public class Dog implements Pet {
    private final String speak;

    public Dog(String speak) {
        this.speak = speak;
    }

    @Override
    public String reply() {
        return speak;
    }
}
