package org.severstal.parser.domain.tatneft;

import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.etpets.EtpEtsBuilder;
import org.severstal.parser.domain.tenderpro.Item;

import java.util.List;

public class TatneftBuilder {
    private List<Item> items;
    private String link;
    private String domain;

    public TatneftBuilder setItems(List<Item> items) {
        this.items = items;

        return this;
    }

    public TatneftBuilder setInfo(String link, String domain) {
        this.link = link;
        this.domain = domain;

        return this;
    }

    public Tender Build() {
        return new TatneftTender(this.items, this.link, this.domain);
    }
}

