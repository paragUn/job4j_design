package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        T oldType = get(index);
        container[index] = newValue;
        return oldType;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T removedT = get(index);
        if (index < size - 1) {
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    size - index - 1
            );
        }
        container[size - 1] = null;
        size--;
        return removedT;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size());
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private T[] grow() {
         return size != 0
                 ? Arrays.copyOf(container, size * 2)
                 : Arrays.copyOf(container, 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("doesn't has next");
                }
                return container[count++];
            }
        };
    }

}
