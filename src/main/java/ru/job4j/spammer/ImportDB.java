package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }



    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            try (Statement st = cnt.createStatement()) {
                st.execute(String.format("create table if not exists users(%s, %s, %s);",
                        "id serial primary key", "name text", "email text"));
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(s -> s.split(";"))
                    .filter(this::validate)
                    .forEach(map -> users.add(new User(map[0], map[1])));
        }
        return users;
    }

    private boolean validate(String[] s) {
        if (s.length != 2) {
            throw new IllegalArgumentException("wrong length");
        }
        if (s[0].isBlank() || s[1].isBlank()) {
            throw new IllegalArgumentException("name or email is blank");
        }
        if (!s[1].contains("@")) {
            throw new IllegalArgumentException("this email: does not contain the @");
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "src/main/java/ru/job4j/spammer/dump.txt");
        db.save(db.load());
    }
}