package ru.job4j.tdd;

import java.util.Scanner;

public class Fool {
    public static boolean div(int div, int num) {
        return div % num == 0;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var io = new Scanner(System.in);
        while (startAt < 100) {
            String out = String.format(
                    "%s%s",
                    div(startAt, 3) ? "Fizz" : "",
                    div(startAt, 5) ? "Buzz" : "");
            if (out.isEmpty()) {
                out = Integer.toString(startAt);
            }
            System.out.println(startAt);
            var answer = io.nextLine();
            if (!out.equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}