package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {
    @Test
    void whenFizz() {
        assertThat(GetOut.getOut(3)).isEqualTo("Fizz");
    }

    @Test
    void whenBuzz() {
        assertThat(GetOut.getOut(5)).isEqualTo("Buzz");
    }

    @Test
    void whenFizzBuzz() {
        assertThat(GetOut.getOut(15)).isEqualTo("FizzBuzz");
    }

    @Test
    void whenNumber() {
        assertThat(GetOut.getOut(17)).isEqualTo("17");
    }

}