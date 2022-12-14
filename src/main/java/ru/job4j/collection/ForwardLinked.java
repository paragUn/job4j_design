package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public boolean revert() {
        boolean rsl = getSize() > 1;
        if (rsl) {
            Node<T> previous = null;
            Node<T> current = head;
            Node<T> next;
            while (current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
        }
        return rsl;
    }

    public void addFirst(T value) {
       head = new Node<>(value, head);
       size++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("Cant delete first element, head is null.");
        }
        T element = head.value;
        Node<T> next = head.next;
        head.value = null;
        head.next = null;
        head = next;
        size--;
        return element;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No elements!(iterator)");
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}