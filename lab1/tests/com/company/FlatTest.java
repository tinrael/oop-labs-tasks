package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlatTest {
    private final Flat flat = new Flat();

    @Test
    void calculateTotalElectricityConsumption() {
        assertEquals(0, flat.calculateTotalElectricityConsumption());

        MicrowaveOven oven = new MicrowaveOven(25);
        ElectricKettle kettle = new ElectricKettle(15, 2.5, 5.0);

        flat.addElectricalAppliance("oven", oven);
        assertEquals(0, flat.calculateTotalElectricityConsumption());
        oven.plug();
        assertEquals(oven.getPower(), flat.calculateTotalElectricityConsumption());

        flat.addElectricalAppliance("kettle", kettle);
        assertEquals(oven.getPower(), flat.calculateTotalElectricityConsumption());
        kettle.plug();
        assertEquals(oven.getPower() + kettle.getPower(), flat.calculateTotalElectricityConsumption());
        oven.unplug();
        assertEquals(kettle.getPower(), flat.calculateTotalElectricityConsumption());
    }
}