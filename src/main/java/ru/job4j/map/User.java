package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        HashMap<User, Object> map = new HashMap<>(16);
        User u1 = new User("Ivan", 2, new GregorianCalendar(2022, Calendar.JANUARY, 1));
        User u2 = new User("Ivan", 2, new GregorianCalendar(2022, Calendar.JANUARY, 1));
        map.put(u1, new Object());
        map.put(u2, new Object());
        System.out.println(map);
        int hc1 = u1.hashCode();
        int hash1 = hc1 ^ (hc1 >>> 16);
        int bucket1 = hash1 & 15;
        System.out.println(bucket1);
        int hc2 = u2.hashCode();
        int hash2 = hc2 ^ (hc2 >>> 16);
        int bucket2 = hash2 & 15;
        System.out.println(bucket2);
        System.out.println(u1.equals(u2));
        System.out.println(map);
    }
}
