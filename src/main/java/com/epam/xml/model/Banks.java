package com.epam.xml.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(namespace = "http://www.example.com/deposits")
@XmlType(name = "banks")
@XmlAccessorType(XmlAccessType.FIELD)
public class Banks {
    @XmlElements(
            {
                    @XmlElement(name = "time-deposit", namespace = "http://www.example.com/deposits", type = TimeDeposit.class),
                    @XmlElement(name = "demand-deposit", namespace = "http://www.example.com/deposits", type = DemandDeposit.class)
            }
    )
    private List<Deposit> deposits;

    public List<Deposit> getDeposits() {
        if (deposits == null) {
            deposits = new ArrayList<>();
        }
        return this.deposits;
    }

    @Override
    public String toString() {
        return "Banks{\n" + deposits + "}";
    }
}
