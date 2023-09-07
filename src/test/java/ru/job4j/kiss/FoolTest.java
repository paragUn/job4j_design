package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {
    @Test
    void whenFizz() {
        GetOut go = new GetOut();
        assertThat(go.getOut(3)).isEqualTo("Fizz");
    }

    @Test
    void whenBuzz() {
        GetOut go = new GetOut();
        assertThat(go.getOut(5)).isEqualTo("Buzz");
    }

    @Test
    void whenFizzBuzz() {
        GetOut go = new GetOut();
        assertThat(go.getOut(15)).isEqualTo("FizzBuzz");
    }

    @Test
    void whenNumber() {
        GetOut go = new GetOut();
        assertThat(go.getOut(17)).isEqualTo("17");
    }

}