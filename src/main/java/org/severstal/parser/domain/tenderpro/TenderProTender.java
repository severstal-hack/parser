package org.severstal.parser.domain.tenderpro;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.TenderDataItem;

import java.util.List;

@Getter
@Setter
public class TenderProTender extends Tender {
    private final List<Item> items;

    public TenderProTender(List<Item> items, String link, String domain) {
        super(link, domain);
        this.items = items;
    }

    @Override
    public String getJSON() {
        Gson gson = new Gson();
        var itemStream = items.stream().map(item -> {
            return new TenderDataItem(this.link,
                    item.getName(),
                    item.getCount(),
                    item.getUnit(),
                    0,
                    null);
        }).toArray();
        return gson.toJson(itemStream);
    }
}

