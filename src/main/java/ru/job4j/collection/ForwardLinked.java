package ru.job4j.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

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
    }

    public T addFirst(T value) {
        Node<T> node = new Node<T>(value, null);
        head = node;
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        head.next = node;
        return head.value;
    }

    public T deleteFirst() {
        T element;
        if (head == null) {
            throw new NoSuchElementException();
        }
            element = head.value;
            Node<T> next = head.next;
            head.value = null;
            head.next = null;
            head = next;
        return element;
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
                    throw new NoSuchElementException();
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