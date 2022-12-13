package ru.job4j.searchfiles;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(
                new String[]{"-d=C:\\projects\\job4j_design", "-n=*.?xt", "-t=mask", "-o=log.txt"}
        );
//        System.out.println(argsName.get("d"));
//        System.out.println(argsName.get("n"));
//        System.out.println(argsName.get("t"));
//        System.out.println(argsName.get("o"));
//        howToSearch(argsName.get("t"));
        Path start = Paths.get((argsName.get("d")));
        Search.search(start, p -> p.toFile().getName().endsWith(path -> path.contains()).forEach(System.out::println));

    }

    private static Predicate howToSearch(String income) {
        if ("mask".equals(income)) {
            return;
        }
        if ("name".equals(income)) {
            return;
        }
        if ("regex".equals(income)) {
            return;
        } else {
            throw new IllegalArgumentException(String.format(
                    "Wrong type of search: %s", income)
             );
        }
    }
}
