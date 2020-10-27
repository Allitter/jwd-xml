package com.epam.xml.parser.dom;

import com.epam.xml.builder.AbstractDepositBuilder;
import com.epam.xml.builder.DemandDepositBuilder;
import com.epam.xml.builder.TimeDepositBuilder;
import com.epam.xml.exception.ParserException;
import com.epam.xml.model.Deposit;
import com.epam.xml.model.DepositParameter;
import com.epam.xml.model.DepositType;
import com.epam.xml.parser.XmlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepositDOMParser implements XmlParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEMAND_DEPOSIT = DepositType.DEMAND_DEPOSIT.getTagName();
    private static final String TIME_DEPOSIT = DepositType.TIME_DEPOSIT.getTagName();
    public static final int FIRST_NODE_INDEX = 0;
    private AbstractDepositBuilder depositBuilder;
    private DocumentBuilder docBuilder;

    public DepositDOMParser() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Ошибка конфигурации парсера: ", e);
        }
    }

    @Override
    public List<Deposit> parse(String path) throws ParserException {
        try {
            Document doc = docBuilder.parse(path);
            Element root = doc.getDocumentElement();
            NodeList depositElementList = root.getChildNodes();
            List<Deposit> deposits = new ArrayList<>();
            for (int i = 0; i < depositElementList.getLength(); i++) {
                Node node = depositElementList.item(i);
                if (isDepositNode(node)) {
                    Element depositElement = (Element) node;
                    Deposit deposit = buildDeposit(depositElement);
                    deposits.add(deposit);
                }
            }

            return deposits;
        } catch (ParseException | IOException | SAXException e) {
            throw new ParserException(e);
        }
    }

    private boolean isDepositNode(Node node) {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return false;
        }

        Element depositElement = (Element) node;
        String name = depositElement.getTagName();
        return DEMAND_DEPOSIT.equals(name) || TIME_DEPOSIT.equals(name);
    }

    private Deposit buildDeposit(Element depositElement) throws ParseException {
        String name = depositElement.getTagName();
        if (DEMAND_DEPOSIT.equals(name)) {
            // Demand deposit parameters
            String monthlyFee = depositElement.getAttribute(DepositParameter.MONTHLY_FEE.getTagName());
            String withdrawalLimit = depositElement.getAttribute(DepositParameter.WITHDRAWAL_LIMIT.getTagName());

            DemandDepositBuilder demandDepositBuilder = new DemandDepositBuilder();
            demandDepositBuilder.setMonthlyFee(monthlyFee);
            demandDepositBuilder.setWithdrawalLimit(withdrawalLimit);
            depositBuilder = demandDepositBuilder;
        } else if (TIME_DEPOSIT.equals(name)) {
            // Time deposit parameters
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            NodeList timeConstraintsList = depositElement.getElementsByTagName(DepositParameter.TIME_CONSTRAINTS.getTagName());
            Element timeConstraints = (Element) timeConstraintsList.item(FIRST_NODE_INDEX);
            String startDateValue = getElementTextContent(timeConstraints, DepositParameter.START_DATE.getTagName());
            String maturityDateValue = getElementTextContent(timeConstraints, DepositParameter.MATURITY_DATE.getTagName());
            Date startDate = format.parse(startDateValue);
            Date maturityDate = format.parse(maturityDateValue);

            TimeDepositBuilder timeDepositBuilder = new TimeDepositBuilder();
            timeDepositBuilder.setStartDate(startDate);
            timeDepositBuilder.setMaturityDate(maturityDate);
            depositBuilder = timeDepositBuilder;
        }

        // Deposit common parameters
        String profitability = depositElement.getAttribute(DepositParameter.PROFITABILITY.getTagName());
        String bankName = depositElement.getAttribute(DepositParameter.BANK_NAME.getTagName());
        depositBuilder.setProfitability(profitability);
        depositBuilder.setBankName(bankName);

        String accountId = getElementTextContent(depositElement, DepositParameter.ACCOUNT_ID.getTagName());
        String depositor = getElementTextContent(depositElement, DepositParameter.DEPOSITOR.getTagName());
        depositBuilder.setDepositor(depositor);
        depositBuilder.setAccountId(accountId);

        return depositBuilder.build();
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(FIRST_NODE_INDEX);
        return node.getTextContent();
    }
}
