package com.epam.xml.builder;

import com.epam.xml.model.DemandDeposit;

public class DemandDepositBuilder extends AbstractDepositBuilder {
    private int monthlyFee;
    private int withdrawalLimit;

    @Override
    public DemandDeposit build() {
        return new DemandDeposit(this);
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(int monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void setMonthlyFee(String monthlyFee) {
        if (monthlyFee == null || monthlyFee.isEmpty()) {
            this.monthlyFee = 0;
        } else {
            this.monthlyFee = Integer.parseInt(monthlyFee);
        }
    }

    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(int withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    public void setWithdrawalLimit(String withdrawalLimit) {
        if (withdrawalLimit == null || withdrawalLimit.isEmpty()) {
            this.withdrawalLimit = 0;
        } else {
            this.withdrawalLimit = Integer.parseInt(withdrawalLimit);
        }
    }
}
