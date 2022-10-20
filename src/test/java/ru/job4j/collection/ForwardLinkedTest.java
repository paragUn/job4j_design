package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;
    private ForwardLinked<Integer> linkedForRevert;

    @BeforeEach
    public void init() {
        linked = new ForwardLinked<>();
        linkedForRevert = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
    }

    @Test
    void whenDeleteFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThatThrownBy(linked::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiDelete() {
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    void whenSize0ThenReturnFalse() {
        assertThat(linkedForRevert.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        linkedForRevert.add(1);
        assertThat(linkedForRevert.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        linkedForRevert.add(1);
        linkedForRevert.add(2);
        linkedForRevert.add(3);
        linkedForRevert.add(4);
        assertThat(linkedForRevert).containsSequence(1, 2, 3, 4);
        assertThat(linkedForRevert.revert()).isTrue();
        assertThat(linkedForRevert).containsSequence(4, 3, 2, 1);
    }
}