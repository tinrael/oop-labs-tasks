package com.company;

import com.example.planes.Warplane;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPlanesBuilder {
    protected Set<Warplane> planes = new HashSet<>();

    public Set<Warplane> getPlanes() {
        return planes;
    }

    abstract public void build(String xmlLocation);

}
