package com.company;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

// syntax-check and validates against the specified XSD
public class XMLValidator {

    public static boolean validate(String xmlLocation, String xsdLocation) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File xsdFile = new File(xsdLocation);
        try {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlLocation);
            validator.validate(source);
        } catch (SAXException | IOException e) {
            System.err.println("[validator]: " + e.getMessage());
            return false;
        }

        System.out.println("[validator]: " + xmlLocation + " is valid.");
        return true;
    }

}
