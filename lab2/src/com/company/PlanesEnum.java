package com.company;

public enum PlanesEnum {
    PLANES("planes"),
    WARPLANE("warplane"),
    ID("id"),
    MODEL("model"),
    ORIGIN("origin"),
    CHARS("chars"),
    TYPE("type"),
    SEATS("seats"),
    ARMAMENTS("armaments"),
    ROCKETS("rockets"),
    RADAR("radar"),
    EQUIPPED("equipped"),
    PARAMETERS("parameters"),
    LENGTH("length"),
    HEIGHT("height"),
    WINGSPAN("wingspan"),
    PRICE("price");

    private String name;

    PlanesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
