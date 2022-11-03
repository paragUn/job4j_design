package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        Map<Integer, String> map = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            if (!map.containsKey(user.getId())) {
                deleted++;
            }
          if (map.containsKey(user.getId()) && !map.containsValue(user.getName())) {
                changed++;
            }
        }
        return new Info(current.size() + deleted - previous.size(), changed, deleted);
    }
}