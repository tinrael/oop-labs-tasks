package com.company;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Flat {
    private HashMap<String, ElectricalAppliance> electricalAppliances = new LinkedHashMap<>();

    public void addElectricalAppliance(String name, ElectricalAppliance electricalAppliance) {
        electricalAppliances.put(name, electricalAppliance);
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
