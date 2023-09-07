package ru.job4j.kiss;

import java.util.Scanner;

public class Fool {
    public static void main(String[] args) {
        GetOut go = new GetOut();
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var io = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(go.getOut(startAt));
            startAt++;
            var answer = io.nextLine();
            if (!answer.equals(go.getOut(startAt))) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}

