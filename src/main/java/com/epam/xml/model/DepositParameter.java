package com.epam.xml.model;

public enum DepositParameter {
    DEPOSITOR("depositor"),
    ACCOUNT_ID("account-id"),
    PROFITABILITY("profitability"),
    BANK_NAME("bank-name"),
    MONTHLY_FEE("monthly-fee"),
    WITHDRAWAL_LIMIT("withdrawal-limit"),
    TIME_CONSTRAINTS("time-constraints"),
    START_DATE("start-date"),
    MATURITY_DATE("maturity-date");

    private final String tagName;

    DepositParameter(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public static boolean containsTag(String tagName) {
        int i = 0;
        DepositParameter[] values = DepositParameter.values();
        while (i < values.length) {
            if (values[i].tagName.equals(tagName)) {
                return true;
            }
            i++;
        }

        return false;
    }

    public static DepositParameter getParameterByTag(String tagName) {
        int i = 0;
        DepositParameter[] values = DepositParameter.values();
        while (i < values.length) {
            if (values[i].tagName.equals(tagName)) {
                return values[i];
            }
            i++;
        }

        throw new IllegalArgumentException("Incorrect tag name");
    }
}
