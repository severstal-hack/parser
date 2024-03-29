package org.severstal.parser.domain.tenderpro;

import org.severstal.parser.domain.Tender;

import java.util.ArrayList;
import java.util.List;

public class TenderProBuilder {
    private String name;
    private List<Item> items;

    private String link;
    private String domain;

    public TenderProBuilder setInfo(String link, String domain) {
        this.link = link;
        this.domain = domain;

        return this;
    }


    public TenderProBuilder setItems(List<Item> items) {
        this.items = items;

        return this;
    }

    public Tender Build() {
        return new TenderProTender(this.items, this.link, this.domain);
    }

}