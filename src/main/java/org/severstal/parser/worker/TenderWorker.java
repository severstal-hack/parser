package org.severstal.parser.worker;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.severstal.parser.domain.Tender;

public abstract class TenderWorker {
    protected final String path;
    protected final Browser browser;
    protected final Page page;

    protected final String link;
    protected final String domain;

    public TenderWorker(String path, Browser browser, String link, String domain) {
        this.link = link;
        this.domain = domain;
        this.path = path;
        this.browser = browser;
        this.page = browser.newPage();

        this.page.navigate(path);
    }

    abstract public Tender run();
}