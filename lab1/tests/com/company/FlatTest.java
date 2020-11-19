package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlatTest {
    private final Flat flat = new Flat();

    @Test
    void addElectricalAppliance() {
        assertThrows(NullPointerException.class, () -> {
            flat.addElectricalAppliance(null, null);
        });

        assertThrows(NullPointerException.class, () -> {
            flat.addElectricalAppliance("oven", null);
        });

        assertThrows(NullPointerException.class, () -> {
            flat.addElectricalAppliance(null, new MicrowaveOven(25));
        });
    }

    @Test
    void getElectricalApplianceByName() {
        String name = "oven";
        MicrowaveOven oven = new MicrowaveOven(25);

        assertNull(flat.getElectricalAppliance(name));

        flat.addElectricalAppliance(name, oven);
        assertNotNull(flat.getElectricalAppliance(name));

        assertNull(flat.getElectricalAppliance("kettle"));
    }

    @Test
    void getElectricalApplianceByPower() {
        MicrowaveOven oven = new MicrowaveOven(25);
        ElectricKettle kettle = new ElectricKettle(15, 2.5, 5.0);

        assertNull(flat.getElectricalAppliance(oven.getPower()));
        assertNull(flat.getElectricalAppliance(kettle.getPower()));

        flat.addElectricalAppliance("oven", oven);
        flat.addElectricalAppliance("kettle", kettle);

        assertNotNull(flat.getElectricalAppliance(oven.getPower()));
        assertNotNull(flat.getElectricalAppliance(kettle.getPower()));
    }

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