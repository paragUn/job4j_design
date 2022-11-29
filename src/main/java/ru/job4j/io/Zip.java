package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static String directory = "d";
    private static String exclude = "e";
    private static String output = "o";

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path currentPath : sources) {
                zip.putNextEntry(new ZipEntry(currentPath.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(currentPath.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName args) {
        File file = new File(args.get(directory));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Path is not directory: %s ", file.getAbsolutePath()));
        }
        if (!args.get(exclude).startsWith(".") && args.get(exclude).length() < 2) {
            throw new IllegalArgumentException("Args 'e' must start with '.' symbol");
        }
        if (!args.get(output).endsWith(".zip")) {
            System.out.println(args.get(output));
            throw new IllegalArgumentException("Args must have .zip extension");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    String.format("wrong number of args %s", args.length));
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<Path> listPaths = Search.search(Paths.get(argsName.get(directory)),
                p -> !p.toFile().getName().endsWith(argsName.get(exclude)));
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
            zip.packFiles(listPaths, new File(argsName.get(output)));
    }
}