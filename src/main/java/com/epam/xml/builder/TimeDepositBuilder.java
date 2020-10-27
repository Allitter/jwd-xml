package com.epam.xml.builder;

import com.epam.xml.model.TimeConstraints;
import com.epam.xml.model.TimeDeposit;

import java.util.Date;

public class TimeDepositBuilder extends AbstractDepositBuilder {
    private Date startDate;
    private Date maturityDate;

    public TimeDepositBuilder() {
    }

    public TimeConstraints getTimeConstraints() {
        return new TimeConstraints(startDate, maturityDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    @Override
    public TimeDeposit build() {
        return new TimeDeposit(this);
    }
}
