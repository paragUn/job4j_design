package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
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
        File file = new File(args.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Проверьте, что путь - это директория, а не файл: %s ", file.getAbsolutePath()));
        }
        if (!args.get("e").startsWith(".") && args.get("e").length() < 2) {
            throw new IllegalArgumentException("Аргумент 'e' должен начинаться с точки!");
        }
        if (!args.get("o").endsWith(".zip")) {
            System.out.println(args.get("o"));
            throw new IllegalArgumentException("Аргумент должен иметь расширение .zip");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    String.format("wrong number of args %s", args.length));
        }
        Zip zip = new Zip();
//        zip.packSingleFile(
//                new File("./pom.xml"),
//                new File("./pom.zip")
//        );
        ArgsName argsName = ArgsName.of(args);
//        System.out.println(argsName.get("d"));
//        System.out.println(argsName.get("e"));
//        System.out.println(argsName.get("o"));
        validate(argsName);

//        for (String arg : args) {
//            System.out.println(arg);
//        }

            List<Path> listPaths = Search.search(Paths.get(argsName.get("d")),
                    p -> p.toFile().getName().endsWith(argsName.get("c")));
            zip.packFiles(listPaths, Paths.get("e").toFile());
    }
}