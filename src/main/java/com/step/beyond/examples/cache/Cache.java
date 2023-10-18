package com.step.beyond.examples.cache;

public interface Cache<T> {

    boolean put(String key, T data);

    T get(String key);
}
