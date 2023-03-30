package ru.job4j.gc.leak;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final Map<Integer, Post> POSTS = new HashMap<>();

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public Post add(Post post) {
        int id = atomicInteger.getAndIncrement();
        post.setId(id);
        POSTS.put(id, post);
        return post;
    }

    public void removeAll() {
        POSTS.clear();
    }

    public static Collection<Post> getPosts() {
        return POSTS.values();
    }
}