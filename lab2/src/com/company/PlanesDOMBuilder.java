package com.company;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.example.planes.Warplane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

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
            System.err.println("[dom-builder]: File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("[dom-builder]: Parsing failure: " + e);
        }
    }

    private Warplane createWarplane(Element warplaneElement) {
        Warplane warplane = new Warplane();

        return warplane;
    }

}
