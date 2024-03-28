package org.severstal.parser.worker;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.tenderpro.Doc;
import org.severstal.parser.domain.tenderpro.Item;
import org.severstal.parser.domain.tenderpro.TenderProBuilder;

import java.util.ArrayList;
import java.util.List;

public class TenderProWorker extends TenderWorker {
    private final TenderProBuilder builder = new TenderProBuilder();

    public TenderProWorker(String path, Browser browser) {
        super(path, browser);
    }

    @Override
    public Tender run() {
        this.parseSubject();
        this.parseInfo();
        this.parseDocs();
        this.parseItems();

        return this.builder.Build();
    }

    private void parseSubject() {
        var locator = this.page.locator(".text__word-break");
        var name = locator.first().textContent();

        this.builder.setName(name);
    }

    private void parseInfo() {
        var info_block = this.page.locator("#tender_organizer");

        var tags = info_block.locator(".tags > .tags__item").all().stream().map(Locator::textContent).toList();

        var organizer = this.page.locator("#tender_organizer > div.pt-16.pb-24.pl-24.pr-24 > a").textContent();
        var comment = this.page.locator("#tender_organizer > div.pt-16.pb-24.pl-24.pr-24 > div.tender-comment__wrapper").textContent();
        this.builder.setInfo(tags, organizer, comment);
    }

    private void parseDocs() {
        var docs_block = this.page.locator("#tender_docs > table > tbody");
        var trs = docs_block.locator("tr");
        List<Doc> docs = new ArrayList<Doc>();
        for (int i = 1; i < trs.count(); ++i) {
            var row = trs.all().get(i);
            var a = row.locator("a");
            var link = a.getAttribute("href");
            docs.add(new Doc(row.textContent(), link));
        }

        this.builder.setDocs(docs);
    }

    private void parseItems() {
        var trs = this.page.locator("#tGoods > div.table-history > div");
        List<Item> items = new ArrayList<Item>();
        for (int i = 2; i < trs.count(); ++i) {
            var row = trs.all().get(i);
            var row_elements = row.locator("div");
            var name = row_elements.locator("p:nth-child(1) > a").textContent();
            var count = row_elements.all().get(2).textContent();
            var unit = row_elements.all().get(3).textContent();
            items.add(new Item(name, Double.parseDouble(count), unit));
        }
        System.out.println(items);

        this.builder.setItems(items);
    }
}