package com.epam.xml.model;

import com.epam.xml.builder.TimeDepositBuilder;
import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(namespace = "http://www.example.com/deposits")
@XmlType(name = "time-deposit")
@XmlAccessorType(XmlAccessType.FIELD)
public class TimeDeposit extends Deposit {
    @XmlElement(name = "time-constraints", namespace = "http://www.example.com/deposits", required = true)
    private TimeConstraints timeConstraints;

    public TimeDeposit() {
    }

    public TimeDeposit(TimeDepositBuilder timeDepositBuilder) {
        super(timeDepositBuilder);
        timeConstraints = timeDepositBuilder.getTimeConstraints();
    }

    public TimeConstraints getTimeConstraints() {
        return timeConstraints;
    }

    @Override
    public String toString() {
        return "TimeDeposit{" + super.toString() +
                " timeConstraints=" + timeConstraints +
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
        TimeDeposit that = (TimeDeposit) o;
        return Objects.equals(getTimeConstraints(), that.getTimeConstraints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTimeConstraints());
    }
}
