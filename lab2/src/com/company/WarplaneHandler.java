package com.company;

import com.example.planes.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class WarplaneHandler extends DefaultHandler {
    private final Set<Warplane> planes = new HashSet<>();

    private PlanesEnum currentPlanesEnum;

    private Warplane currentWarplane;
    private Chars currentChars;
    private Parameters currentParameters;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        currentPlanesEnum = PlanesEnum.valueOf(localName.toUpperCase());

        switch (currentPlanesEnum) {
            case WARPLANE:
                currentWarplane = new Warplane();
                currentWarplane.setId(attrs.getValue(0));
                break;
            case CHARS:
                currentChars = new Chars();
                break;
            case ARMAMENTS:
                Armaments armaments = new Armaments();
                armaments.setRockets(Integer.parseInt(attrs.getValue(0)));

                currentChars.setArmaments(armaments);
                break;
            case RADAR:
                Radar radar = new Radar();
                radar.setEquipped(Boolean.parseBoolean(attrs.getValue(0)));

                currentChars.setRadar(radar);
                break;
            case PARAMETERS:
                currentParameters = new Parameters();
                break;
            default:
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        currentPlanesEnum = PlanesEnum.valueOf(localName.toUpperCase());

        switch (currentPlanesEnum) {
            case WARPLANE:
                planes.add(currentWarplane);
                break;
            case CHARS:
                currentWarplane.setChars(currentChars);
                break;
            case PARAMETERS:
                currentWarplane.setParameters(currentParameters);
                break;
            default:
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String elementTextContent = new String(ch, start, length); // trim() ?

        switch (currentPlanesEnum) {
            case MODEL:
                currentWarplane.setModel(elementTextContent);
                break;
            case ORIGIN:
                currentWarplane.setOrigin(elementTextContent);
                break;
            case TYPE:
                currentChars.setType(Type.valueOf(elementTextContent.toUpperCase()));
                break;
            case SEATS:
                currentChars.setSeats(Integer.parseInt(elementTextContent));
                break;
            case LENGTH:
                currentParameters.setLength(new BigDecimal(elementTextContent));
                break;
            case HEIGHT:
                currentParameters.setHeight(new BigDecimal(elementTextContent));
                break;
            case WINGSPAN:
                currentParameters.setWingspan(new BigDecimal(elementTextContent));
                break;
            case PRICE:
                currentWarplane.setPrice(new BigInteger(elementTextContent));
                break;
            default:
        }
    }

    public Set<Warplane> getPlanes() {
        return planes;
    }

}
