package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenTest {
    private final int POWER = 20;

    private final MicrowaveOven oven = new MicrowaveOven(POWER);

    @Test
    void instantiate() {
        assertEquals(POWER, oven.getPower());
        assertFalse(oven.isConnectToElectricitySupply());
        assertFalse(oven.isWorking());
        assertTrue(oven.isClose());
    }

    @Test
    void plug() {
        oven.plug();
        assertTrue(oven.isConnectToElectricitySupply());
    }

    @Test
    void unplug() {
        oven.plug();
        oven.close();
        oven.turnOn();

        oven.unplug();
        assertFalse(oven.isConnectToElectricitySupply());
        assertFalse(oven.isWorking());
        assertTrue(oven.isClose());
    }

    @Test
    void turnOn() {
        oven.turnOff();
        oven.unplug();
        oven.open();

        oven.turnOn();
        assertFalse(oven.isWorking());
        oven.plug();
        oven.turnOn();
        assertFalse(oven.isWorking());
        oven.close();
        oven.turnOn();
        assertTrue(oven.isWorking());
    }

    @Test
    void turnOff() {
        oven.plug();
        oven.close();
        oven.turnOn();

        oven.turnOff();
        assertTrue(oven.isConnectToElectricitySupply());
        assertFalse(oven.isWorking());
        assertTrue(oven.isClose());
    }

    @Test
    void open() {
        oven.open();
        assertFalse(oven.isClose());
    }

    @Test
    void close() {
        oven.close();
        assertTrue(oven.isClose());
    }
}