package org.severstal.parser.worker;

import com.microsoft.playwright.Browser;
import lombok.extern.slf4j.Slf4j;
import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.etpets.EtpEtsBuilder;
import org.severstal.parser.domain.tenderpro.Item;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "worker.etp-ets")
public class EtpEtsWorker extends TenderWorker {
    private final EtpEtsBuilder builder;
    private final String type;

    public EtpEtsWorker(String path, Browser browser, String type, String link, String domain) {
        super(path, browser, link, domain);
        this.type = type;
        this.builder = new EtpEtsBuilder();
        this.builder.setInfo(link, domain);
    }

    @Override
    public Tender run() {
        parseItems();

        return this.builder.Build();
    }

    private void parseItems() {
        log.info("starting parse etp-ets; URL: {}", this.page.url());
        switch (this.type) {
            case "44":
                this.parse44();
                break;
            case "223":
                this.parse223();
                break;
        }
    }

    private void parse44() {
        var tbody = this.page.locator("tbody").first();
        var trs = tbody.locator(".row-item.row-parent");
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < trs.count(); ++i) {
            var row = trs.all().get(i);
            var tds = row.locator("td");
            var name = tds.all().get(3).textContent();
            var count = tds.all().get(4).textContent();
            var unit = tds.all().get(2).textContent();
            items.add(new Item(name, Double.parseDouble(count.replace(",",".")), unit));
        }

        this.builder.setItems(items);
    }

    private void parse223() {
        var fieldset = this.page.locator("fieldset.collection-grid").last();
        var trs = fieldset.locator("tbody > tr.form-block");
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < trs.count(); ++i) {
            var row = trs.all().get(i);
            var tds = row.locator("td");
            var name = tds.all().get(2).textContent();
            var count = tds.all().get(9).textContent();
            var unit = tds.all().get(8).textContent();
            items.add(new Item(name, Double.parseDouble(count), unit));
        }

        this.builder.setItems(items);
    }
}
