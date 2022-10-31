package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> nodeParent = findBy(parent);
        if (nodeParent.isPresent() && findBy(child).isEmpty()) {
            nodeParent.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean isBinary() {
        return findByPredicate(f -> f.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E val) {
        return findByPredicate(f -> f.value.equals(val));

    }
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

}