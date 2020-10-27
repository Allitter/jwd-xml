package com.epam.xml.model;

public enum DepositType {
    DEMAND_DEPOSIT("demand-deposit"),
    TIME_DEPOSIT("time-deposit");

    private final String tagName;

    DepositType(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
