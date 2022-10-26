package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];
    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        boolean rsl = false;
        int index = indexFor(hash(key));
        if (table[index] == null) {
            table[index] = new MapEntry<K, V>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] rsl = new MapEntry[capacity * 2];
        capacity = rsl.length;
        for (MapEntry<K, V> entry : table) {
            if (entry == null) {
                continue;
            }
            int index = indexFor(hash(entry.key));
            rsl[index] = entry;
        }
        table = rsl;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash(key));
        if (table[index] != null && hash(key) == hash(table[index].key) && Objects.equals(table[index].key, key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key));
        if (table[index] != null && hash(key) == hash(table[index].key) && Objects.equals(table[index].key, key)) {
            table[index] = null;
            modCount++;
            count--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int exceptionalModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (exceptionalModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
