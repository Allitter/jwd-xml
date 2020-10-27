package com.epam.xml.parser.sax;

import com.epam.xml.builder.AbstractDepositBuilder;
import com.epam.xml.builder.DemandDepositBuilder;
import com.epam.xml.builder.TimeDepositBuilder;
import com.epam.xml.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DepositSaxHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEMAND_DEPOSIT = DepositType.DEMAND_DEPOSIT.getTagName();
    private static final String TIME_DEPOSIT = DepositType.TIME_DEPOSIT.getTagName();
    private final List<Deposit> deposits;
    private AbstractDepositBuilder depositBuilder;
    private String attributeValue;

    public DepositSaxHandler() {
        deposits = new ArrayList<>();
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (DEMAND_DEPOSIT.equals(localName) || TIME_DEPOSIT.equals(localName)) {
            if (DepositType.DEMAND_DEPOSIT.getTagName().equals(localName)) {
                String withdrawalLimit = attributes.getValue(DepositParameter.WITHDRAWAL_LIMIT.getTagName());
                String monthlyFee = attributes.getValue(DepositParameter.MONTHLY_FEE.getTagName());

                DemandDepositBuilder demandDepositBuilder = new DemandDepositBuilder();
                demandDepositBuilder.setWithdrawalLimit(withdrawalLimit);
                demandDepositBuilder.setMonthlyFee(monthlyFee);
                depositBuilder = demandDepositBuilder;
            } else {
                depositBuilder = new TimeDepositBuilder();
            }

            String profitability = attributes.getValue(DepositParameter.PROFITABILITY.getTagName());
            String bankName = attributes.getValue(DepositParameter.BANK_NAME.getTagName());

            depositBuilder.setProfitability(profitability);
            depositBuilder.setBankName(bankName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (DEMAND_DEPOSIT.equals(localName) || TIME_DEPOSIT.equals(localName)) {
            Deposit deposit = depositBuilder.build();
            deposits.add(deposit);
        } else {
            if (DepositParameter.containsTag(localName)) {
                DepositParameter parameter = DepositParameter.getParameterByTag(localName);
                try {
                    setAttribute(parameter);
                } catch (ParseException e) {
                    LOGGER.error("Incorrect date format", e);
                }
            }
        }
    }

    private void setAttribute(DepositParameter parameterName) throws ParseException {
        TimeDepositBuilder timeDepositBuilder;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        switch (parameterName) {
            case DEPOSITOR :
                depositBuilder.setDepositor(attributeValue);
                break;
            case ACCOUNT_ID :
                depositBuilder.setAccountId(attributeValue);
                break;
            case START_DATE :
                Date startDate = format.parse(attributeValue);;
                timeDepositBuilder = (TimeDepositBuilder)depositBuilder;
                timeDepositBuilder.setStartDate(startDate);
                break;
            case MATURITY_DATE :
                Date maturityDate = format.parse(attributeValue);
                timeDepositBuilder = (TimeDepositBuilder)depositBuilder;
                timeDepositBuilder.setMaturityDate(maturityDate);
                break;
            case TIME_CONSTRAINTS:
                break;
            default:
                throw new IllegalArgumentException("Incorrect attribute name " + parameterName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        attributeValue = new String(ch, start, length);
    }
}
