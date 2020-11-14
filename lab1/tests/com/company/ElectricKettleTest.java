package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricKettleTest {
    private final int POWER = 15;
    private final double MIN_VOLUME = 1.5;
    private final double MAX_VOLUME = 5.0;

    private final ElectricKettle kettle = new ElectricKettle(POWER, MIN_VOLUME, MAX_VOLUME);

    @Test
    void instantiate() {
        assertEquals(POWER, kettle.getPower());
        assertFalse(kettle.isConnectToElectricitySupply());
        assertFalse(kettle.isWorking());
        assertEquals(MIN_VOLUME, kettle.getMinVolume());
        assertEquals(MAX_VOLUME, kettle.getMaxVolume());
        assertEquals(0.0, kettle.getCurrentVolume());
    }

    @Test
    void plug() {
        kettle.plug();
        assertTrue(kettle.isConnectToElectricitySupply());
    }

    @Test
    void unplug() {
        kettle.plug();
        kettle.empty();
        kettle.fill(MIN_VOLUME);
        kettle.turnOn();

        kettle.unplug();
        assertFalse(kettle.isConnectToElectricitySupply());
        assertFalse(kettle.isWorking());
    }

    @Test
    void turnOn() {
        kettle.turnOff();
        kettle.unplug();
        kettle.empty();

        kettle.turnOn();
        assertFalse(kettle.isWorking());
        kettle.plug();
        kettle.turnOn();
        assertFalse(kettle.isWorking());
        kettle.fill(MIN_VOLUME);
        kettle.turnOn();
        assertTrue(kettle.isWorking());
        kettle.turnOff();
        kettle.empty();
        kettle.fill(MAX_VOLUME);
        kettle.turnOn();
        assertTrue(kettle.isWorking());
        kettle.turnOff();
        kettle.empty();
        kettle.fill(MAX_VOLUME + 0.5);
        kettle.turnOn();
        assertFalse(kettle.isWorking());

        ElectricKettle otherKettle = new ElectricKettle(17, 2.5, 7.0);
        otherKettle.fill(2.0);
        otherKettle.plug();
        otherKettle.turnOn();
        assertFalse(otherKettle.isWorking());
        otherKettle.fill(0.5);
        otherKettle.turnOn();
        assertTrue(otherKettle.isWorking());
        otherKettle.turnOff();
        otherKettle.fill(3.0);
        otherKettle.turnOn();
        assertTrue(otherKettle.isWorking());
    }

    @Test
    void turnOff() {
        kettle.plug();
        kettle.empty();
        kettle.fill(MIN_VOLUME);
        kettle.turnOn();

        kettle.turnOff();
        assertTrue(kettle.isConnectToElectricitySupply());
        assertFalse(kettle.isWorking());
    }

    @Test
    void fill() {
        final double litres = 1.3;

        kettle.empty();
        kettle.fill(litres);
        assertEquals(litres, kettle.getCurrentVolume());
        kettle.fill(litres);
        assertEquals(litres + litres, kettle.getCurrentVolume());
        kettle.empty();
        kettle.fill(-litres);
        assertEquals(0.0, kettle.getCurrentVolume());
    }

    @Test
    void empty() {
        kettle.empty();
        assertEquals(0.0, kettle.getCurrentVolume());
    }
}