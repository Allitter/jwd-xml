package com.epam.xml.parser;

import com.epam.xml.builder.DemandDepositBuilder;
import com.epam.xml.builder.TimeDepositBuilder;
import com.epam.xml.exception.ParserException;
import com.epam.xml.model.BankName;
import com.epam.xml.model.DemandDeposit;
import com.epam.xml.model.Deposit;
import com.epam.xml.model.TimeDeposit;
import org.junit.Assert;
import org.junit.Before;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractXmlParserTest {
    private static final String PATH_TO_XML_FILE = "src/test/resources/test_deposits.xml";
    private static final String INCORRECT_FILE_PATH = "NOT EXITING FILE";
    private XmlParser xmlParser;

    @Before
    public void toDoBeforeTest() {
        setParser();
    }

    public abstract void setParser();

    public void setParser(XmlParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    public void testParseShouldReturnListWhenInputIsCorrect() throws ParserException, ParseException {
        //given
        DemandDepositBuilder demandDepositBuilder = new DemandDepositBuilder();
        demandDepositBuilder.setDepositor("Vladislav");
        demandDepositBuilder.setAccountId("ID1233214650");
        demandDepositBuilder.setProfitability(5);
        demandDepositBuilder.setBankName(BankName.BELAGROPROMBANK);
        demandDepositBuilder.setMonthlyFee(50);
        demandDepositBuilder.setWithdrawalLimit(10);
        DemandDeposit firstDeposit = demandDepositBuilder.build();

        TimeDepositBuilder timeDepositBuilder = new TimeDepositBuilder();
        timeDepositBuilder.setDepositor("Alexey");
        timeDepositBuilder.setAccountId("ID1233214654");
        timeDepositBuilder.setProfitability(1000);
        timeDepositBuilder.setBankName(BankName.BELARUSBANK);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        timeDepositBuilder.setStartDate(format.parse("2018-02-10"));
        timeDepositBuilder.setMaturityDate(format.parse("2023-03-11"));
        TimeDeposit secondDeposit = timeDepositBuilder.build();

        // when
        List<Deposit> actual = xmlParser.parse(PATH_TO_XML_FILE);

        // then
        List<Deposit> expected = Arrays.asList(firstDeposit, secondDeposit);
        Assert.assertEquals(expected, actual);
    }

    public void testParseShouldThrowExceptionWhenFilePathIsIncorrect() throws ParserException {
        // when
        List<Deposit> actual = xmlParser.parse(INCORRECT_FILE_PATH);
    }
}
