package org.severstal.parser.domain.tatneft;

import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.tenderpro.Item;

import java.util.List;

public class TatneftBuilder {
    private List<Item> items;

    public TatneftBuilder setItems(List<Item> items) {
        this.items = items;

        return this;
    }

    public Tender Build() {
        return new TatneftTender(this.items);
    }
}

