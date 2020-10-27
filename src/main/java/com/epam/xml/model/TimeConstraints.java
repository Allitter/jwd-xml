package com.epam.xml.model;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class TimeConstraints {
    @XmlElement(name = "start-date", namespace = "http://www.example.com/deposits", required = true)
    @XmlSchemaType(name = "date")
    private Date startDate;
    @XmlElement(name = "maturity-date", namespace = "http://www.example.com/deposits", required = true)
    @XmlSchemaType(name = "date")
    private Date maturityDate;

    public TimeConstraints() {
    }

    public TimeConstraints(Date startDate, Date maturityDate) {
        this.startDate = startDate;
        this.maturityDate = maturityDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    @Override
    public String toString() {
        return "TimeConstraints{" +
                "startDate=" + startDate +
                ", maturityDate=" + maturityDate +
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
        TimeConstraints that = (TimeConstraints) o;
        return Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getMaturityDate(), that.getMaturityDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getMaturityDate());
    }
}
