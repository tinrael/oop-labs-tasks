package com.company;

public abstract class ElectricalAppliance {
    // electric power (watts)
    private int power;

    ElectricalAppliance(int power) {
        this.power = power;
    }

    // to connect a piece of electrical equipment to an electricity supply
    public abstract boolean plug();

    // to get the electric power (watts)
    public int getPower() {
        return power;
    }
}
