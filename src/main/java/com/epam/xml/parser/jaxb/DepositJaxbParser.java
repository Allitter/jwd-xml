package com.epam.xml.parser.jaxb;

import com.epam.xml.exception.ParserException;
import com.epam.xml.model.Banks;
import com.epam.xml.model.Deposit;
import com.epam.xml.parser.XmlParser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class DepositJaxbParser implements XmlParser {
    @Override
    public List<Deposit> parse(String xmlPath) throws ParserException {
        try {
            JAXBContext jc = JAXBContext.newInstance(Banks.class);
            Unmarshaller um = jc.createUnmarshaller();
            File xmlFile = new File(xmlPath);
            Banks banks = (Banks) um.unmarshal(xmlFile);

            return banks.getDeposits();
        } catch (JAXBException e) {
            throw new ParserException(e);
        }
    }
}
