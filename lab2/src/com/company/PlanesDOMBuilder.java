package com.company;

import com.example.planes.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.istack.internal.NotNull;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class PlanesDOMBuilder extends AbstractPlanesBuilder{
    private DocumentBuilder documentBuilder;

    public PlanesDOMBuilder() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void build(String xmlLocation) {
        try {
            Document document = documentBuilder.parse(xmlLocation);
            Element rootElement = document.getDocumentElement();
            NodeList warplanesNodeList = rootElement.getElementsByTagName("warplane");

            for (int i = 0; i < warplanesNodeList.getLength(); i++) {
                Element warplaneElement = (Element) warplanesNodeList.item(i);
                Warplane warplane = createWarplane(warplaneElement);
                planes.add(warplane);
            }
        } catch (IOException e) {
            System.err.println("[dom-builder]: File error or I/O error: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("[dom-builder]: Parsing failure: " + e.getMessage());
        }
    }

    private Warplane createWarplane(Element warplaneElement) {
        Warplane warplane = new Warplane();

        warplane.setId(warplaneElement.getAttribute("id"));
        warplane.setModel(getElementTextContent(warplaneElement, "model"));
        warplane.setOrigin(getElementTextContent(warplaneElement, "origin"));

        Element charsElement = (Element) warplaneElement.getElementsByTagName("chars").item(0);
        Chars chars = new Chars();
        chars.setType(Type.valueOf(getElementTextContent(charsElement, "type").toUpperCase()));
        chars.setSeats(Integer.parseInt(getElementTextContent(charsElement, "seats")));

        NodeList armamentsNodeList = warplaneElement.getElementsByTagName("armaments");
        if (armamentsNodeList.getLength() != 0) {
            Element armamentsElement = (Element) armamentsNodeList.item(0);
            Armaments armaments = new Armaments();
            armaments.setRockets(Integer.parseInt(armamentsElement.getAttribute("rockets")));

            chars.setArmaments(armaments);
        }

        Element radarElement = (Element) charsElement.getElementsByTagName("radar").item(0);
        Radar radar = new Radar();
        radar.setEquipped(Boolean.parseBoolean(radarElement.getAttribute("equipped")));

        chars.setRadar(radar);

        warplane.setChars(chars);

        Element parametersElement = (Element) warplaneElement.getElementsByTagName("parameters").item(0);
        Parameters parameters = new Parameters();
        parameters.setLength(new BigDecimal(getElementTextContent(parametersElement, "length")));
        parameters.setHeight(new BigDecimal(getElementTextContent(parametersElement, "height")));
        parameters.setWingspan(new BigDecimal(getElementTextContent(parametersElement, "wingspan")));

        warplane.setParameters(parameters);

        warplane.setPrice(new BigInteger(getElementTextContent(warplaneElement, "price")));

        return warplane;
    }

    @NotNull
    private static String getElementTextContent(Element element, String childTagName) {
        NodeList childNodesList = element.getElementsByTagName(childTagName);
        if (childNodesList.getLength() == 0) {
            throw new IllegalArgumentException("[dom-builder]: The element '" + element.getTagName() + "' does not have a child node with the name '" + childTagName +"'.");
        } else {
            String text = childNodesList.item(0).getTextContent();
            return text == null ? "" : text;
        }
    }

}
