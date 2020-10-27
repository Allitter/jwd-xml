package com.epam.xml.model;

import com.epam.xml.builder.AbstractDepositBuilder;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlRootElement(namespace = "http://www.example.com/deposits")
@XmlType
@XmlSeeAlso({
        TimeDeposit.class,
        DemandDeposit.class
})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Deposit {
    @XmlElement(namespace = "http://www.example.com/deposits", required = true)
    private String depositor;
    @XmlElement(name = "account-id", namespace = "http://www.example.com/deposits", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String accountId;
    @XmlAttribute(name = "profitability", required = true)
    private int profitability;
    @XmlAttribute(name = "bank-name")
    private BankName bankName;

    public Deposit() {
    }

    public Deposit(AbstractDepositBuilder depositBuilder) {
        depositor = depositBuilder.getDepositor();
        bankName = depositBuilder.getBankName();
        profitability = depositBuilder.getProfitability();
        accountId = depositBuilder.getAccountId();
    }

    public String getDepositor() {
        return depositor;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getProfitability() {
        return profitability;
    }

    public BankName getBankName() {
        if (bankName == null) {
            return BankName.BELARUSBANK;
        } else {
            return bankName;
        }
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositor='" + getDepositor() + '\'' +
                ", accountId='" + getAccountId() + '\'' +
                ", profitability=" + getProfitability() +
                ", bankName=" + getBankName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deposit deposit = (Deposit) o;
        return getProfitability() == deposit.getProfitability() &&
                Objects.equals(getDepositor(), deposit.getDepositor()) &&
                Objects.equals(getAccountId(), deposit.getAccountId()) &&
                getBankName() == deposit.getBankName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepositor(), getAccountId(), getProfitability(), getBankName());
    }
}
