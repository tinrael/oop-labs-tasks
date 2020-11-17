package com.company;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Flat {
    private HashMap<String, ElectricalAppliance> electricalAppliances = new LinkedHashMap<>();

    public void addElectricalAppliance(String name, ElectricalAppliance electricalAppliance) {
        electricalAppliances.put(name, electricalAppliance);
    }

    /*
     * Searches for the ElectricalAppliance by the name 'name'.
     * If found, returns ElectricalAppliance.
     * Otherwise, returns null.
     */
    public ElectricalAppliance getElectricalAppliance(String name) {
        return electricalAppliances.get(name);
    }

    /*
     * Searches for the ElectricalAppliance with the power 'power'.
     * If found, returns ElectricalAppliance.
     * Otherwise, returns null.
     */
    public ElectricalAppliance getElectricalAppliance(int power) {
        for (ElectricalAppliance electricalAppliance : electricalAppliances.values()) {
            if (electricalAppliance.getPower() == power) {
                return electricalAppliance;
            }
        }
        return null;
    }

    public int calculateTotalElectricityConsumption() {
        int result = 0;
        for (ElectricalAppliance electricalAppliance : electricalAppliances.values()) {
            if (electricalAppliance.isConnectToElectricitySupply()) {
                result += electricalAppliance.getPower();
            }
        }
        return result;
    }
}