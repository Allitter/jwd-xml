package com.epam.xml.parser;

import com.epam.xml.exception.ParserException;
import com.epam.xml.model.Deposit;
import java.util.List;

public interface XmlParser {

    List<Deposit> parse(String xmlPath) throws ParserException;

}
