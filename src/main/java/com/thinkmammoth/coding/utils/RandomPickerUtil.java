package com.thinkmammoth.coding.utils;

import java.util.List;

public class RandomPickerUtil<T> {
    private final List<T> list;

    public RandomPickerUtil(List<T> list) {
        this.list = list;
    }

    public T pick() {
        return list.get(pickRandomNumber(list.size()));
    }

    private int pickRandomNumber(int max) {
        return (int) (Math.random() * max);
    }
}
