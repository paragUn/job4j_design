package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("Roman");
    }

    @Test
    void whenKeyNameWithoutValueAndPasswordWithValue() {
        String path = "./data/key_without_value.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("password")).isEqualTo("password");
        assertThatThrownBy(() -> config.value("name"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Something wrong");
    }
    @Test
    void whenIncorrectStartsComment() {
        String path = "./data/value_without_key.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("password")).isEqualTo("password");
        assertThatThrownBy(() -> config.value(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Something wrong");
    }

    @Test
    void whenMultipleEqualSigns() {
        String path = "./data/multiple_equal_signs.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("roman=1");
    }

    @Test
    void whenOnlyEqual() {
        String path = "./data/only_equal.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Something wrong");
    }
}