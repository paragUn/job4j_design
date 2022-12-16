package ru.job4j.searchfiles;

import java.util.HashMap;
import java.util.Map;


public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String result = values.get(key);
        if (result == null) {
            throw new IllegalArgumentException("Not found some argument");
        }
        return result;
    }

    private void parse(String[] args) {
        for (String arg: args) {
            validate(arg);
            String key = arg.substring(1, arg.indexOf("="));
            String value = arg.substring(arg.indexOf("=") + 1);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("args is empty");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void validate(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException(
                    String.format("this name: %s is incorrect!", name));
        }
        if (!name.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain the symbol \"=\"", name));
        }
        if (!name.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a ", name));
        }
        if (name.startsWith("-=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a key", name));
        }
        if (name.indexOf("=") == name.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a value", name));
        }
    }
}