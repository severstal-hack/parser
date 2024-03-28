package org.severstal.parser.domain.tenderpro;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.severstal.parser.domain.Tender;
import java.util.List;

@Getter
@Setter
public class TenderProTender implements Tender {
    private final String name;
    private final List<Tag> tags;
    private final Info info;
    private final List<Doc> docs;
    private final List<Item> items;

    public TenderProTender(String name, List<Tag> tags, Info info, List<Doc> docs, List<Item> items) {
        this.name = name;
        this.tags = tags;
        this.info = info;
        this.docs = docs;
        this.items = items;
    }

    @Override
    public String getJSON() {
        Gson gson = new Gson();
        return gson.toJson(this.items);
    }
}

