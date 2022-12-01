package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
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
        int[] indexesOfColumn = new int[filter.split(delimiter).length]; // массив индексов
        List<String> keywordFilter = Arrays.asList(filter.split(delimiter)); //массив ключевых слов
        List<String>  columns = Arrays.asList(readLine.get(0).split(delimiter));
        for (String current : keywordFilter) {
            if (columns.contains(keywordFilter)) {
                int i = 0;
                indexesOfColumn[i] = columns.indexOf(keywordFilter);
                i++;
            }
        }
        System.out.println(indexesOfColumn);
        for (String current : readLine) {
            for (int index : indexesOfColumn) {
                str.add(current.split(delimiter)[index]);
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
}