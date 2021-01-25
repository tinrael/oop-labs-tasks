package com.company;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class PlanesSAXBuilder extends AbstractPlanesBuilder {
    private final WarplaneHandler warplaneHandler = new WarplaneHandler();
    private XMLReader xmlReader;

    public PlanesSAXBuilder() {
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(warplaneHandler);
        } catch (SAXException e) {
            System.err.print("[sax-builder]: SAX parser error: " + e.getMessage());
        }
    }

    @Override
    public void build(String xmlLocation) {
        try {
            xmlReader.parse(xmlLocation);
        } catch (SAXException e) {
            System.err.print("[sax-builder]: SAX parser error: " + e.getMessage());
        } catch (IOException e) {
            System.err.print("[sax-builder]: I/O error: " + e.getMessage());
        }
        planes = warplaneHandler.getPlanes();
    }

}
