package ru.job4j.collection;

import java.util.*;


public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    int modCount;
    private class Node<E> {
        E item;
        Node<E> next;
        Node(E e, Node<E> next) {
            this.item = e;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int exceptModCount = modCount;
            Node<E> point = first;
            E value;

            @Override
            public boolean hasNext() {
                if (exceptModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No such element");
                }
                value = point.item;
                point = point.next;
                return value;
            }
        };
    }



}
