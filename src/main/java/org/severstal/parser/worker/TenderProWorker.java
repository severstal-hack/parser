package org.severstal.parser.worker;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import lombok.extern.slf4j.Slf4j;
import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.tenderpro.Item;
import org.severstal.parser.domain.tenderpro.TenderProBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TenderProWorker extends TenderWorker {
    private final TenderProBuilder builder = new TenderProBuilder();

    public TenderProWorker(String path, Browser browser, String link, String domain) {
        super(path, browser, link, domain);
        this.builder.setInfo(link, domain);
    }

    @Override
    public Tender run() {
        this.parseItems();

        return this.builder.Build();
    }

    private void parseItems() {
        log.info("starting parse tenderpro; URL: {}", this.page.url());
        var trs = this.page.locator("#tGoods > div.table-history > div");
        List<Item> items = new ArrayList<Item>();
        for (int i = 2; i < trs.count(); ++i) {
            var row = trs.all().get(i);
            var row_elements = row.locator("div");
            var name = row_elements.locator("p:nth-child(1) > a").textContent();
            var count = row_elements.all().get(2).textContent();
            var unit = row_elements.all().get(3).textContent();
            items.add(new Item(name, Double.parseDouble(count.replace(",", ".")), unit));
        }
        System.out.println(items);

        this.builder.setItems(items);
    }
}