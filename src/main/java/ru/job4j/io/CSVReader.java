package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        checkArgs(argsName);
        List<String> readLines = readCSV(argsName.get("path"));
        String changedLine = lineChanger(readLines, argsName.get("delimiter"), argsName.get("filter"));
        saveCSV(changedLine, argsName.get("out"));
    }

    private static List<String> readCSV(String path) throws FileNotFoundException {
            try (Scanner scanner = new Scanner(new FileReader(path))) {
                List<String> res = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    res.add(scanner.nextLine());
                }
                return res;
            }
    }

    private static String lineChanger(List<String> readLine, String delimiter, String filter) {
        var ls = System.lineSeparator();
        List<Integer> intList = new ArrayList<>();
        List<String> keywordFilter = Arrays.asList(filter.split(","));
        List<String>  columns = Arrays.asList(readLine.get(0).split(delimiter));
        keywordFilter.forEach(s -> intList.add(columns.indexOf(s)));
        List<String> result = new ArrayList<>();
        StringJoiner joiner;
        for (String current : readLine) {
            joiner = new StringJoiner(delimiter);
            for (int index : intList) {
                String[] splittedReadline = current.split(delimiter);
                joiner.add(splittedReadline[index]);
            }
            result.add(joiner.toString());
        }
        return String.join(ls, result);
    }

    private static void saveCSV(String outData, String outPath) {
        if (outPath.equals("stdout")) {
            System.out.println(outData);
            return;
        }
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(outPath, true))) {
            pw.println(outData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void checkArgs(ArgsName args) {
        Path source = Paths.get(args.get("path"));
        if (!source.toFile().exists() || !source.toFile().isFile()) {
            throw new IllegalArgumentException("Path not valid, or not exist.");
        }
        if (args.get("delimiter") == null) {
            throw new IllegalArgumentException("Delimiter must be defined.");
        }
    }
    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}