package org.severstal.parser.domain.tatneft;

import org.severstal.parser.domain.Tender;

public class TatneftBuilder {
    public Tender Build() {
        return new TatneftTender();
    }
}

