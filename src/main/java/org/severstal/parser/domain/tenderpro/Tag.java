package org.severstal.parser.domain.tenderpro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag {
    private final String name;

    public Tag(String name) {
        this.name = name;
    }
}
