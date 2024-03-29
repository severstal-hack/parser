package org.severstal.parser.domain.tatneft;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.tenderpro.Item;

import java.util.List;

@Getter
@Setter
public class TatneftTender implements Tender {
    private final List<Item> items;

    public TatneftTender(List<Item> items) {
        this.items = items;
    }

    @Override
    public String getJSON() {
        Gson gson = new Gson();
        return gson.toJson(this.items);
    }
}
