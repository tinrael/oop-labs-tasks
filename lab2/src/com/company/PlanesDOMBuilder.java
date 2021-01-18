package com.company;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PlanesDOMBuilder extends AbstractPlanesBuilder{
    private DocumentBuilder documentBuilder;

    public PlanesDOMBuilder() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println(e);
        }
    }

    @Override
    public void build(String xmlLocation) {

    }
}
