package com.epam.xml.parser.sax;

import com.epam.xml.exception.ParserException;
import com.epam.xml.model.Deposit;
import com.epam.xml.parser.XmlParser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;
import java.util.List;

public class DepositSaxParser implements XmlParser {
    @Override
    public List<Deposit> parse(String path) throws ParserException {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            DepositSaxHandler handler = new DepositSaxHandler();
            reader.setContentHandler(handler);
            reader.parse(path);
            return handler.getDeposits();
        } catch (SAXException | IOException e) {
            throw new ParserException(e);
        }
    }
}
