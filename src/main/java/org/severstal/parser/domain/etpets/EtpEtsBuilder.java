package org.severstal.parser.domain.etpets;

import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.tenderpro.Item;

import java.util.List;

public class EtpEtsBuilder {
    private List<Item> items;

    public EtpEtsBuilder setItems(List<Item> items) {
        this.items = items;

        return this;
    }

    public Tender Build() {
        return new EtpEtsTender(this.items);
    }
}
