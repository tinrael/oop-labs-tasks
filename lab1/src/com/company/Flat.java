package com.company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Flat {
    // an empty insertion-ordered LinkedHashMap instance
    private final HashMap<String, ElectricalAppliance> electricalAppliances = new LinkedHashMap<>();

    public void addElectricalAppliance(String name, ElectricalAppliance electricalAppliance) {
        if (name == null || electricalAppliance == null) {
            throw new NullPointerException();
        }
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

    public void sortElectricalAppliances() {
        List<Map.Entry<String, ElectricalAppliance>> entries = new LinkedList<>(electricalAppliances.entrySet());
        entries.sort(new Comparator<Map.Entry<String, ElectricalAppliance>>() {
            @Override
            public int compare(Map.Entry<String, ElectricalAppliance> o1, Map.Entry<String, ElectricalAppliance> o2) {
                return Integer.compare(o1.getValue().getPower(), o2.getValue().getPower());
            }
        });

        electricalAppliances.clear();
        for (Map.Entry<String, ElectricalAppliance> entry : entries) {
            electricalAppliances.put(entry.getKey(), entry.getValue());
        }
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

    @Override
    public String toString() {
        String result = "Electrical appliances:\n";
        for (Map.Entry<String, ElectricalAppliance> entry : electricalAppliances.entrySet()) {
            result += "\t[" + entry.getKey() + ", " + entry.getValue().getPower() + "]\n";
        }
        return result;
    }
}