package ru.job4j.tdd;

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
}