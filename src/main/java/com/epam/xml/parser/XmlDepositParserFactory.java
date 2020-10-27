package com.epam.xml.parser;

import com.epam.xml.parser.dom.DepositDOMParser;
import com.epam.xml.parser.jaxb.DepositJaxbParser;
import com.epam.xml.parser.sax.DepositSaxParser;

public class XmlDepositParserFactory {

    public XmlParser create(String name) {
        XmlParser parser;
        switch(name.toLowerCase()) {
            case "dom" :
                parser = new DepositDOMParser();
                break;
            case "sax" :
                parser = new DepositSaxParser();
                break;
            case "jaxb" :
                parser = new DepositJaxbParser();
                break;
            default:
                throw new IllegalArgumentException("Incorrect parser name " + name);
        }

        return parser;
    }
}
