package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> bot = readPhrases();
        List<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String str = reader.readLine();
        while (!OUT.equals(str)) {
            if (STOP.equals(str)) {
                while (!CONTINUE.equals(str)) {
                    str = reader.readLine();
                    result.add(str);
                }
            }
            Random r = new Random();
            int resultIndex = r.nextInt(bot.size());
            String answer = bot.get(resultIndex);
            result.add(answer);
            System.out.println(answer);
            str = reader.readLine();
            result.add(str);
        }
        saveLog(result);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            phrases = br.lines()
                    .map(s -> s + System.lineSeparator())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\src\\data\\dialog.txt",
                ".\\src\\data\\random_phrase.txt");
        try {
            cc.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}