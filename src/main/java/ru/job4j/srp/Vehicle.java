package ru.job4j.srp;

/**
 * метод headlightOnOf() может не понадобится, ведь фар у транспортного средства может не быть
 * метод fuelIgnition() может не понадобится, ведь это может быть самокат
 */
public interface Vehicle {
    void fuelIgnition();

    void headlightOnOff();

    void startEngine();
}
