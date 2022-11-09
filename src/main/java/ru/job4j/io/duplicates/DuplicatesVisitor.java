package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<Path> duplicatesList = new HashSet<>();
    Map<FileProperty, Path> map = new HashMap<>();

    public void getDuplicatesList() {
        duplicatesList.forEach(System.out::println);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (map.containsKey(property)) {
            duplicatesList.add(map.get(property).toAbsolutePath());
            duplicatesList.add(file.toAbsolutePath());
        }
        map.put(property, file);
        return super.visitFile(file, attrs);
    }
}