package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> map = new HashMap<>();

    public void getDuplicatesList() {
        for (Map.Entry<FileProperty, List<Path>> current : map.entrySet()) {
            if (current.getValue().size() > 1) {
                current.getValue().forEach(System.out::println);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(), file.getFileName().toString());
            map.putIfAbsent(property, new ArrayList<>());
            map.get(property).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
}