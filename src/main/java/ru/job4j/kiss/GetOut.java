package ru.job4j.kiss;

public class GetOut {
     static String getOut(int number) {
        String out = String.format(
                "%s%s",
                number % 3 == 0 ? "Fizz" : "",
                number % 5 == 0 ? "Buzz" : "");
        out = out.isEmpty() ? Integer.toString(number) : out;
        return out;
    }
}
