package com.company;

import java.util.LinkedList;
import java.util.List;

public class Flat {
    private List<ElectricalAppliance> electricalAppliances = new LinkedList<>();

    public void addElectricalAppliance(ElectricalAppliance appliance) {
        electricalAppliances.add(appliance);
    }
}
