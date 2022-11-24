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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        argsName.get(args[0]);
        try {
            zip.packFiles(Search.search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1])), Paths.get(args[2]).toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}