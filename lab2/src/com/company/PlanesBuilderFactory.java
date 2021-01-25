package com.company;

public class PlanesBuilderFactory {

    public AbstractPlanesBuilder createPlanesBuilder(String parser) {
        switch (parser.toUpperCase()) {
            case "DOM":
                return new PlanesDOMBuilder();
            case "STAX":
                return new PlanesStAXBuilder();
            case "SAX":
                return new PlanesSAXBuilder();
            default:
                throw new IllegalArgumentException("[builder-factory]: No such parser in the builder factory.");
        }
    }

}
