package com.epam.xml.parser.jaxb;

import com.epam.xml.exception.ParserException;
import com.epam.xml.parser.AbstractXmlParserTest;
import org.junit.Test;

import java.text.ParseException;

public class DepositJaxbParserTest extends AbstractXmlParserTest {
    @Override
    public void setParser() {
        setParser(new DepositJaxbParser());
    }

    @Test
    @Override
    public void testParseShouldReturnListWhenInputIsCorrect() throws ParserException, ParseException {
        super.testParseShouldReturnListWhenInputIsCorrect();
    }

    @Test(expected = ParserException.class)
    @Override
    public void testParseShouldThrowExceptionWhenFilePathIsIncorrect() throws ParserException {
        super.testParseShouldThrowExceptionWhenFilePathIsIncorrect();
    }
}