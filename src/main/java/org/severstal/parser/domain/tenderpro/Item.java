package org.severstal.parser.domain.tenderpro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String name;
    private double count;
    private String unit;

    public Item(String name, double count, String unit) {
        this.name = name;
        this.count = count;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("|Item name: %s Count: %f Unit: %s|", name, count, unit);
    }
}
