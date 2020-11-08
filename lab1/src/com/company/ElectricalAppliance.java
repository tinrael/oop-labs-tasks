package com.company;

public abstract class ElectricalAppliance {
    // electric power (watts)
    private final int POWER;

    private boolean isConnectToElectricitySupply = false;

    // status of electrical equipment (is working or is not working)
    private boolean isWorking = false;

    public ElectricalAppliance(int power) {
        this.POWER = power;
    }

    // to connect a piece of electrical equipment to an electricity supply
    public void plug() {
        isConnectToElectricitySupply = true;
    }

    // to stop a piece of electrical equipment being connected to an electricity supply
    public void unplug() {
        isConnectToElectricitySupply = false;
        isWorking = false;
    }

    // to start a piece of electrical equipment working
    public void turnOn() {
        if (isConnectToElectricitySupply) {
            isWorking = true;
        }
    }

    // to stop a piece of electrical equipment working
    public void turnOff() {
        isWorking = false;
    }

    public boolean isConnectToElectricitySupply() {
        return isConnectToElectricitySupply;
    }

    public boolean isWorking() {
        return isWorking;
    }

    // to get the electric power (watts)
    public int getPower() {
        return POWER;
    }
}
