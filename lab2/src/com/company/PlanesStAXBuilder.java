package com.company;

import com.example.planes.*;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class PlanesStAXBuilder extends AbstractPlanesBuilder {
    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    @Override
    public void build(String xmlLocation) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(xmlLocation));
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            int type;
            String currentElementName;

            while (reader.hasNext()) {
                type = reader.next();

                if (type == XMLStreamConstants.START_ELEMENT) {
                    currentElementName = reader.getLocalName();

                    if (WarplaneChildElement.valueOf(currentElementName.toUpperCase()) == WarplaneChildElement.WARPLANE) {
                        Warplane warplane = createWarplane(reader);
                        planes.add(warplane);
                    }
                }
            }
        } catch (XMLStreamException e) {
            System.err.println("[stax-builder]: StAX parsing error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("[stax-builder]: File " + xmlLocation + " not found: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.err.println("[stax-builder]: Impossible close file " + xmlLocation + ": " + e.getMessage());
                }
            }
        }
    }

    private Warplane createWarplane(XMLStreamReader reader) throws XMLStreamException {
        Warplane warplane = new Warplane();
        warplane.setId(reader.getAttributeValue(null, WarplaneChildElement.ID.getName()));

        int type;
        String currentElementName;

        while (reader.hasNext()) {
            type = reader.next();
            currentElementName = reader.getLocalName();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    switch (WarplaneChildElement.valueOf(currentElementName.toUpperCase())) {
                        case MODEL:
                            warplane.setModel(getCurrentElementTextContent(reader));
                            break;
                        case ORIGIN:
                            warplane.setOrigin(getCurrentElementTextContent(reader));
                            break;
                        case CHARS:
                            warplane.setChars(createChars(reader));
                            break;
                        case PARAMETERS:
                            warplane.setParameters(createParameters(reader));
                            break;
                        case PRICE:
                            warplane.setPrice(new BigInteger(getCurrentElementTextContent(reader)));
                            break;
                    }

                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if (WarplaneChildElement.valueOf(currentElementName.toUpperCase()) == WarplaneChildElement.WARPLANE) {
                        return warplane;
                    }

                    break;
            }
        }

        throw new XMLStreamException("[stax-builder]: Unknown element inside the <warplane>.");
    }

    private Chars createChars(XMLStreamReader reader) throws XMLStreamException {
        Chars chars = new Chars();

        int type;
        String currentElementName;

        while (reader.hasNext()) {
            type = reader.next();
            currentElementName = reader.getLocalName();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    switch (WarplaneChildElement.valueOf(currentElementName.toUpperCase())) {
                        case TYPE:
                            chars.setType(Type.valueOf(getCurrentElementTextContent(reader).toUpperCase()));
                            break;
                        case SEATS:
                            chars.setSeats(Integer.parseInt(getCurrentElementTextContent(reader)));
                            break;
                        case ARMAMENTS:
                            Armaments armaments = new Armaments();
                            armaments.setRockets(Integer.parseInt(reader.getAttributeValue(null, WarplaneChildElement.ROCKETS.getName())));

                            chars.setArmaments(armaments);
                            break;
                        case RADAR:
                            Radar radar = new Radar();
                            radar.setEquipped(Boolean.parseBoolean(reader.getAttributeValue(null, WarplaneChildElement.EQUIPPED.getName())));

                            chars.setRadar(radar);
                            break;
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (WarplaneChildElement.valueOf(currentElementName.toUpperCase()) == WarplaneChildElement.CHARS) {
                        return chars;
                    }

                    break;
            }
        }

        throw new XMLStreamException("[stax-builder]: Unknown element inside the <chars>.");
    }

    private Parameters createParameters(XMLStreamReader reader) throws XMLStreamException {
        Parameters parameters = new Parameters();

        int type;
        String currentElementName;

        while (reader.hasNext()) {
            type = reader.next();
            currentElementName = reader.getLocalName();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    switch (WarplaneChildElement.valueOf(currentElementName.toUpperCase())) {
                        case LENGTH:
                            parameters.setLength(new BigDecimal(getCurrentElementTextContent(reader)));
                            break;
                        case HEIGHT:
                            parameters.setHeight(new BigDecimal(getCurrentElementTextContent(reader)));
                            break;
                        case WINGSPAN:
                            parameters.setWingspan(new BigDecimal(getCurrentElementTextContent(reader)));
                            break;
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (WarplaneChildElement.valueOf(currentElementName.toUpperCase()) == WarplaneChildElement.PARAMETERS) {
                        return parameters;
                    }

                    break;
            }
        }

        throw new XMLStreamException("[stax-builder]: Unknown element inside the <parameters>.");
    }

    @NotNull
    private String getCurrentElementTextContent(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }

        return text == null ? "" : text;
    }

}
