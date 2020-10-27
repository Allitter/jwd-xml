package com.epam.xml.model;

import com.epam.xml.builder.DemandDepositBuilder;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(namespace = "http://www.example.com/deposits")
@XmlType(name = "demand-deposit")
@XmlAccessorType(XmlAccessType.FIELD)
public class DemandDeposit extends Deposit {
    @XmlAttribute(name = "monthly-fee")
    private int monthlyFee;
    @XmlAttribute(name = "withdrawal-limit")
    private int withdrawalLimit;

    public DemandDeposit() {
    }

    public DemandDeposit(DemandDepositBuilder demandDepositBuilder) {
        super(demandDepositBuilder);
        monthlyFee = demandDepositBuilder.getMonthlyFee();
        withdrawalLimit = demandDepositBuilder.getWithdrawalLimit();
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    @Override
    public String toString() {
        return "DemandDeposit{" + super.toString() +
                " monthlyFee=" + monthlyFee +
                ", withdrawalLimit=" + withdrawalLimit +
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
        if (!super.equals(o)) {
            return false;
        }
        DemandDeposit deposit = (DemandDeposit) o;
        return monthlyFee == deposit.monthlyFee &&
                withdrawalLimit == deposit.withdrawalLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), monthlyFee, withdrawalLimit);
    }
}
