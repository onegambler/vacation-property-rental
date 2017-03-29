package com.magale.roberto.repository;

import java.util.Collection;

public interface Repository<K, T> {

    T get(K key);

    void add(T item);

    void delete(K key);

    void update(K key, T item);

}
