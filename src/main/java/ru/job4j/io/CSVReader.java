package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        checkArgs(argsName);
       List<String> readLines = readCSV(argsName.get("path"));
        String changedLine = lineChanger(readLines,
                argsName.get("delimiter"),
                argsName.get("filter"));
        saveCSV(changedLine, argsName.get("out"));
    }

    private static List<String> readCSV(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(path));
        List<String> res = new ArrayList<>();
        while (scanner.hasNext()) {
            res.add(scanner.nextLine());
        }
        return res;
    }

//-path=file.csv -delimiter=;  -out=stdout -filter=name,age
//                "name;age;last_name;education",
//                "Tom;20;Smith;Bachelor",
//                "Jack;25;Johnson;Undergraduate",
//                "William;30;Brown;Secondary special"
    private static String lineChanger(List<String> readLine, String delimiter, String filter) {
        StringJoiner str = new StringJoiner(delimiter); // выдача результата
        List<Integer> intList = new ArrayList<>(); // массив индексов

        List<String> keywordFilter = Arrays.asList(filter.split(",")); //массив ключевых слов
//        System.out.println("keywordFilter: " +  keywordFilter);

        List<String>  columns = Arrays.asList(readLine.get(0).split(delimiter));
//        System.out.println("columns: " +  columns);

//        for (String s : keywordFilter) {
//            intList.add(columns.indexOf(s));
//        }
        keywordFilter.forEach(s -> intList.add(columns.indexOf(s)));
//        System.out.println("columns[0] = " + columns.get(0));
//        System.out.println("keywordFilter[0] = " + keywordFilter.get(0));

//        for (Integer i : intList) {
//            System.out.println("index = " + i);
//        }

//        readLine.forEach(System.out::println);

//        List<String> splittedReadline =
        for (String current : readLine) {
            for (int index : intList) {
                String[] splittedReadline = current.split(delimiter);
                str.add(splittedReadline[index]);
            }
            str.add(System.lineSeparator());
        }
        return str.toString();
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
}