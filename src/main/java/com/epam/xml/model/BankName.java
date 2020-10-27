package com.epam.xml.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "bank-name")
@XmlEnum
public enum BankName {
    BELARUSBANK,
    BELINVESTBANK,
    BELAGROPROMBANK,
    BELGAZPROMBANK,
    PARITETBANK,
}
