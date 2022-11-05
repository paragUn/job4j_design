package ru.job4j.io;

import java.io.*;
public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String reader;
            boolean server = true;
            while ((reader = in.readLine()) != null) {
                if (server && (reader.startsWith("400") || (reader.startsWith("500")))) {
                    out.append(reader.split(" ")[1]);
                    server = false;
                }
                if (!server && (reader.startsWith("200") || (reader.startsWith("300")))) {
                    out.append(";").append(reader.split(" ")[1]).append(System.lineSeparator());
                    server = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.log", "unavailable.csv");
    }
}