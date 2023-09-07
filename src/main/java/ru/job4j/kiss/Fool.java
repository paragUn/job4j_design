package ru.job4j.kiss;

import java.util.Scanner;

public class Fool {
    public static boolean div(int div, int num) {
        return div % num == 0;
    }

    public static String getOut(int number) {
        String out = String.format(
                "%s%s",
                div(number, 3) ? "Fizz" : "",
                div(number, 5) ? "Buzz" : "");
        out = out.isEmpty() ? Integer.toString(number) : out;
        return out;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var io = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(getOut(startAt));
            startAt++;
            var answer = io.nextLine();
            if (!answer.equals(getOut(startAt))) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}