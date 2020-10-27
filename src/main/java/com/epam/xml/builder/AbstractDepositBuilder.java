package com.epam.xml.builder;

import com.epam.xml.model.BankName;
import com.epam.xml.model.Deposit;

public abstract class AbstractDepositBuilder {
    private String depositor;
    private BankName bankName;
    private int profitability;
    private String accountId;

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public BankName getBankName() {
        return bankName;
    }

    public void setBankName(BankName bankName) {
        if (bankName == null) {
            this.bankName = BankName.BELARUSBANK;
        } else {
            this.bankName = bankName;
        }
    }

    public void setBankName(String bankName) {
        if (bankName == null || bankName.isEmpty()) {
            this.bankName = BankName.BELARUSBANK;
        } else {
            bankName = bankName.toUpperCase();
            this.bankName = BankName.valueOf(bankName);
        }
    }

    public int getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public void setProfitability(String profitability) {
        if (profitability == null || profitability.isEmpty()) {
            this.profitability = 0;
        } else {
            this.profitability = Integer.parseInt(profitability);
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public abstract Deposit build();
}
