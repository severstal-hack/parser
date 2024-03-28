package org.severstal.parser.domain.tenderpro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doc {
    private final String filename;
    private final String link;
    private String ext;

    public Doc(String filename, String link) {
        this.filename = filename;
        this.link = link;

        this.setExtension();
    }

    private void setExtension() {
        int i = this.filename.lastIndexOf('.');
        if (i > 0) {
            this.ext = this.filename.substring(i + 1);
        }
    }

    @Override
    public String toString() {
        return String.format("Filename: %s Link: %s Ext: %s", this.filename, this.link, this.ext);
    }
}
