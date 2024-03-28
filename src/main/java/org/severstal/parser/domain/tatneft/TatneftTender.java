package org.severstal.parser.domain.tatneft;

import lombok.Getter;
import lombok.Setter;
import org.severstal.parser.domain.Tender;

@Getter
@Setter
public class TatneftTender implements Tender {

    public TatneftTender() {
    }

    @Override
    public String getJSON() {
        return null;
    }
}
