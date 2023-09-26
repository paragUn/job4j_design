package ru.job4j.srp;

/**
 * Нарушение srp.
 * Метод saveLogs необходимо выделить отдельно, так как не описывает меню.
 */
public interface Menu {

     void showMenu();

     void menuItem1();

     void menuItem2();

     void saveLogs();
}
