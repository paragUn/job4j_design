package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены.")
public class GeneratorTest {

    @Test
    public void whenAllValidThenTestPassed() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Ivan Ivanov");
        args.put("subject", "you");
        String expected = "I am a Ivan Ivanov, Who are you? ";
        assertThat(expected).isEqualTo(generator.produce(template, args));
    }

    @Test
    public void whenTemplateIsNull() {
        Generator generator = new TemplateGenerator();
        String template = null;
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Ivan Ivanov");
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void templateHasThreeKeys() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, my surname is ${surname}. Who are ${subject}? ";
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Ivan Ivanov");
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("template has too much arguments!");
    }

    @Test
    public void mapHasThreePairs() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "James");
        args.put("surname", "Bond");
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("map has too much arguments!");
    }
}