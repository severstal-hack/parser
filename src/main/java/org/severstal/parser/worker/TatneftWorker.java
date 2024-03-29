package org.severstal.parser.worker;

import com.microsoft.playwright.Browser;
import lombok.extern.slf4j.Slf4j;
import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.tatneft.TatneftBuilder;
import org.severstal.parser.domain.tenderpro.Item;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "worker.tatneft")
public class TatneftWorker extends TenderWorker {

    private final TatneftBuilder builder = new TatneftBuilder();

    public TatneftWorker(String path, Browser browser, String link, String domain) {
        super(path, browser, link, domain);
        this.builder.setInfo(link, domain);
    }

    @Override
    public Tender run() {
        parseItems();

        return this.builder.Build();
    }

    private void parseItems() {
        log.info("starting parse tatneft; URL: {}", this.page.url());
        var trs = this.page.locator("#main > tbody > tr:nth-child(2) > td.center > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(3) > td > table:nth-child(8) > tbody:nth-child(2) > tr");
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < trs.count(); ++i) {
            var row = trs.all().get(i);
            var tds = row.locator("td");
            var name = tds.all().get(1).textContent();
            var count = tds.all().get(2).textContent();
            var unit = tds.all().get(3).textContent();
            items.add(new Item(name, Double.parseDouble(count.replace(",",".")), unit));
        }

        this.builder.setItems(items);
    }
}
