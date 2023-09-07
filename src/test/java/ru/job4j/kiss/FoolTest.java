package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void whenRemIsZeroWithFive() {
        assertThat(Fool.div(10, 5)).isTrue();
    }

    @Test
    void whenRemIsZeroWithThree() {
        assertThat(Fool.div(9, 3)).isTrue();
    }

    @Test
    void whenRemIsNotZeroWithFive() {
        assertThat(Fool.div(9, 5)).isFalse();
    }

    @Test
    void whenRemIsNotZeroWithThree() {
        assertThat(Fool.div(10, 3)).isFalse();
    }

    @Test
    void whenFizz() {
        assertThat(Fool.getOut(3)).isEqualTo("Fizz");
    }

    @Test
    void whenBuzz() {
        assertThat(Fool.getOut(5)).isEqualTo("Buzz");
    }

    @Test
    void whenFizzBuzz() {
        assertThat(Fool.getOut(15)).isEqualTo("FizzBuzz");
    }

    @Test
    void whenNumber() {
        assertThat(Fool.getOut(17)).isEqualTo("17");
    }

}