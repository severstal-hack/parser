package org.severstal.parser.domain;


abstract public class Tender {
    protected final String link;
    protected final String domain;

    protected Tender(String link, String domain) {
        this.link = link;
        this.domain = domain;
    }

    abstract public String getJSON();
}
