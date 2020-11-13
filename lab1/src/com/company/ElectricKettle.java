package com.company;

public class ElectricKettle extends ElectricalAppliance {
    private final double MIN_VOLUME; // litres
    private final double MAX_VOLUME; // litres

    private double currentVolume = 0.0; // litres

    public ElectricKettle(int power, double minVolume, double maxVolume) {
        super(power);
        this.MIN_VOLUME = minVolume;
        this.MAX_VOLUME = maxVolume;
    }

    public void fillWithWater(double litres) {
        currentVolume += litres;
    }

    @Override
    public void turnOn() {
        if ((Double.compare(currentVolume, MIN_VOLUME) >= 0) && (Double.compare(currentVolume, MAX_VOLUME) <= 0)) {
            super.turnOn();
        }
    }

    public double getMinVolume() {
        return this.MIN_VOLUME;
    }

    public double getMaxVolume() {
        return this.MAX_VOLUME;
    }

    public double getCurrentVolume() {
        return this.currentVolume;
    }
}
