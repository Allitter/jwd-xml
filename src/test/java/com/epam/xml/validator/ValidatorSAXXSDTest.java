package com.epam.xml.validator;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorSAXXSDTest {
    public static final String SCHEMA_PATH = "src/test/resources/deposits.xsd";
    public static final String VALID_XML_PATH = "src/test/resources/test_deposits.xml";
    public static final String NOT_VALID_XML_PATH = "src/test/resources/not_valid_deposits.xml";

    @Test
    public void testIsValidShouldReturnTrueWhenFileIsValid() {
        ValidatorSAXXSD validator = new ValidatorSAXXSD(SCHEMA_PATH);

        Assert.assertTrue(validator.isValid(VALID_XML_PATH));
    }

    @Test
    public void testIsValidShouldReturnFalseWhenFileIsNotValid() {
        ValidatorSAXXSD validator = new ValidatorSAXXSD(SCHEMA_PATH);

        Assert.assertFalse(validator.isValid(NOT_VALID_XML_PATH));
    }
}