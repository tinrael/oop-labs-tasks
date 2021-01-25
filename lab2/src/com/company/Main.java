package com.company;

public class Main {

    public static void main(String[] args) {
        // System.out.println(XMLValidator.validate("data/planes.xml", "data/planes.xsd"));

        PlanesBuilderFactory planesBuilderFactory = new PlanesBuilderFactory();
        AbstractPlanesBuilder planesBuilder = planesBuilderFactory.createPlanesBuilder("sax");
        planesBuilder.build("data/planes.xml");
        System.out.println(planesBuilder.getPlanes());
    }
}
