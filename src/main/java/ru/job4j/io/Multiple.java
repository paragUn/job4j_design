package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiple {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int row = 0; row < 10; row++) {
                for (int cell = 0; cell < 10; cell++) {
                    out.write((row + " * " + cell + " = " + row * cell).getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
