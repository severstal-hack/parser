package org.severstal.parser.domain.etpets;

import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.tenderpro.Item;

import java.util.List;

public class EtpEtsBuilder {
    private List<Item> items;
    private String link;
    private String domain;

    public EtpEtsBuilder setItems(List<Item> items) {
        this.items = items;

        return this;
    }

    public EtpEtsBuilder setInfo(String link, String domain) {
        this.link = link;
        this.domain = domain;

        return this;
    }

    public Tender Build() {
        return new EtpEtsTender(this.items, this.link, this.domain);
    }
}
