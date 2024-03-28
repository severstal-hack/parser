package org.severstal.parser.domain.tenderpro;

import org.severstal.parser.domain.Tender;

import java.util.ArrayList;
import java.util.List;

public class TenderProBuilder {
    private String name;
    private List<String> tags;
    private List<Doc> docs;
    private List<Item> items;
    private String organizer;
    private String comment;

    public TenderProBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public TenderProBuilder setInfo(List<String> tags, String organizer, String comment) {
        this.tags = tags;
        this.organizer = organizer;
        this.comment = comment;

        return this;
    }

    public TenderProBuilder setDocs(List<Doc> docs) {
        this.docs = docs;

        return this;
    }

    public TenderProBuilder setItems(List<Item> items) {
        this.items = items;

        return this;
    }

    public Tender Build() {
        List<Tag> tags = this.tags.stream().map(Tag::new).toList();
        Info info = new Info(this.organizer, this.comment);

        return new TenderProTender(this.name, tags, info, docs, items);
    }

}