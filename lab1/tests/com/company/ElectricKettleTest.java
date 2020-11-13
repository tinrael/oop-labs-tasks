package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricKettleTest {
    private final int POWER = 15;
    private final double MIN_VOLUME = 1.5;
    private final double MAX_VOLUME = 5.0;

    private final ElectricKettle kettle = new ElectricKettle(POWER, MIN_VOLUME, MAX_VOLUME);

    @Test
    void fillWithWater() {
        double delta = 0.0001;

        assertEquals(0.0, kettle.getCurrentVolume(), delta);
        kettle.fillWithWater(3.5);
        assertEquals(3.5, kettle.getCurrentVolume(), delta);
    }
}