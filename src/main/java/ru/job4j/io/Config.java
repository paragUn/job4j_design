package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !s.startsWith("#") && !s.isBlank() && valid(s))
                    .map(s -> s.split("=", 2))
                    .forEach(map -> values.put(map[0], map[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) throws IllegalArgumentException {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Something wrong");
        }
        return values.get(key);
    }

    private boolean valid(String s) {
        if (s.endsWith("=") || s.startsWith("=") || !s.contains("=")) {
            throw new IllegalArgumentException(String.format("incorrect pair: %s", s));
        }
        return true;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config.value("hibernate.connection.driver_class"));
    }
}