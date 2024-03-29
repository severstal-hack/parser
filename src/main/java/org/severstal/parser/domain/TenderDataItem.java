package org.severstal.parser.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenderDataItem {
    private final String link;
    private final String name;
    private final double count;
    private final String unit;
    private final double price;
    private final String address;

    public TenderDataItem(String link, String name, double count, String unit, double price, String address) {
        this.link = link;
        this.name = name;
        this.count = count;
        this.unit = unit;
        this.price = price;
        this.address = address;
    }
}
