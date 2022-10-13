package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRolenameIsAssistant() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Assistant"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Assistant");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Assistant"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.add(new Role("1", "Administrator"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Developer");
    }

    @Test
    void whenReplaceThenRoleIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.replace("1", new Role("1", "Administrator"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Administrator");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.replace("10", new Role("10", "Administrator"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Developer");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Developer");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        boolean rsl = store.replace("1", new Role("1", "Administrator"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        boolean rsl = store.replace("10", new Role("10", "Administrator"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}