package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfDivisibleBy2() {
        ListUtils.removeIf(input, integer -> integer % 2 == 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIfDivisibleBy2() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.replaceIf(input, integer -> integer % 2 == 0, 787);
        assertThat(input).hasSize(3).containsSequence(1, 787, 3);
    }

    @Test
    void whenRemoveAllFromListContains3And787() {
        ListUtils.removeAll(input, Arrays.asList(3, 787));
        assertThat(input).hasSize(1).containsSequence(1);
    }
}