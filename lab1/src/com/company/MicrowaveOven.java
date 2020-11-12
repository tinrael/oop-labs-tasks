package com.company;

public class MicrowaveOven extends ElectricalAppliance {
    private boolean isClose = true;

    public MicrowaveOven(int power) {
        super(power);
    }

    public void open() {
        isClose = false;
    }

    public void close() {
        isClose = true;
    }

    @Override
    public void turnOn() {
        if (isClose) {
            super.turnOn();
        }
    }

    public boolean isClose() {
        return isClose;
    }
}
