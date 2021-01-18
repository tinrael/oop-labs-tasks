package com.company;

public class PlanesBuilderFactory {

    public AbstractPlanesBuilder createPlanesBuilder(String parser) {
        switch (parser.toUpperCase()) {
            case "DOM":
                return new PlanesDOMBuilder();
            case "STAX":
            case "SAX":
                return null;
            default:
                throw new IllegalArgumentException("[builder-factory]: No such parser in the builder factory.");
        }
    }

}
