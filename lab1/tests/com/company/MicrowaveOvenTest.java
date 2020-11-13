package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenTest {
    private final int POWER = 20;

    private final MicrowaveOven oven = new MicrowaveOven(POWER);

    @Test
    void plug() {
        assertFalse(oven.isConnectToElectricitySupply());
        oven.plug();
        assertTrue(oven.isConnectToElectricitySupply());
    }

    @Test
    void open() {
        assertTrue(oven.isClose());
        oven.open();
        assertFalse(oven.isClose());
    }
}