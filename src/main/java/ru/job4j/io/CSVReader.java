package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

//-path=file.csv -delimiter=;  -out=stdout -filter=name,age
//                "name;age;last_name;education",
//                "Tom;20;Smith;Bachelor",
//                "Jack;25;Johnson;Undergraduate",
//                "William;30;Brown;Secondary special"
public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
       List <String> readLines = readCSV(argsName.get("path"));
        String changedLine = lineChanger(readLines,
                argsName.get("delimiter"),
                argsName.get("filter"));
        saveCSV(changedLine, argsName.get("out"));
    }

    private static List<String> readCSV(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(path));
        return scanner.nextLine();
    }

    private static String lineChanger(List<String> readLine, String delimiter, String filter) {
        StringJoiner str = new StringJoiner(readLine);
        String[]
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