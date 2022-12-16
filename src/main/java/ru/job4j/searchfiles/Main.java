package ru.job4j.searchfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) throws IOException {
        List<Path> result;
        ArgsName argsName = ArgsName.of(
                new String[]{"-d=C:\\projects\\job4j_design", "-n=*.?ava", "-t=mask", "-o=log1.txt"}
        );
        validation(argsName);
        Predicate<Path> hts = howToSearch(argsName.get("t"), argsName);
        Path start = Paths.get((argsName.get("d")));
        result = search(start, hts);
        writeResult(argsName.get("o"), result);
    }

    private static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static Predicate<Path> howToSearch(String income, ArgsName argsName) {
        Predicate<Path> filter;
        if ("mask".equals(income)) {
            PathMatcher pathMatcher = FileSystems.getDefault().
                    getPathMatcher("glob:" + argsName.get("n"));
            filter = path -> pathMatcher.matches(Paths.get(path.toFile().getName()));
            return filter;
        }
        if ("name".equals(income)) {
            filter = path -> path.toFile().getName().contains(argsName.get("n"));
            return filter;
        }
        if ("regex".equals(income)) {
            PathMatcher pathMatcher = FileSystems.getDefault().
                    getPathMatcher("regex:" + argsName.get("n"));
            filter = path -> pathMatcher.matches(Paths.get(path.toFile().getName()));
            return filter;
        } else {
            throw new IllegalArgumentException(String.format(
                    "Wrong type of search: %s", income)
             );
        }
    }

    private static void writeResult(String out, List<Path> data) {
        try (PrintWriter printWriter = new PrintWriter(
                new FileWriter(out))) {
            for (Path currentString : data) {
                printWriter.write(currentString.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(ArgsName argsName) {
        if (!Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException(argsName.get("d") + " path doesn't exists.");
        }
        if (argsName.get("d").isEmpty()
                || argsName.get("n").isEmpty()
                || argsName.get("t").isEmpty()
                || argsName.get("o").isEmpty()) {
            throw new IllegalArgumentException("Check your param.");
        }
    }
}
