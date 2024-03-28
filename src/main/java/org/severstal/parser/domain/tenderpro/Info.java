package org.severstal.parser.domain.tenderpro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Info {
    private String organizer;
    private String comment;

    public Info(String organizer, String comment) {
        this.organizer = organizer;
        this.comment = comment;
    }
}
