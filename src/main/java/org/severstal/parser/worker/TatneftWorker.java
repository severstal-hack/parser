package org.severstal.parser.worker;

import com.microsoft.playwright.Browser;
import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.tatneft.TatneftBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TatneftWorker extends TenderWorker {

    private final TatneftBuilder builder = new TatneftBuilder();

    public TatneftWorker(String path, Browser browser) {
        super(path, browser);
    }

    @Override
    public Tender run() {
        parseInfo();

        return this.builder.Build();
    }

    private void parseInfo() {
        HashMap<String, String> m = new HashMap<>();
        var trs = this.page.locator("#main > tbody > tr:nth-child(2) > td.center > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(3) > td > table:nth-child(6) > tbody > tr");
        for (int i = 1; i < trs.count(); ++i) {
            var info_row = trs.all().get(i);
            System.out.printf("ТЕКСТ: %s%n", info_row.textContent());
            if (info_row.textContent().contains("Условия проведения")) {
                var info_trs = info_row.locator("tr");
                for (var info_tr : info_trs.all()) {
                    var info = info_tr.textContent();
                }
            } else {
                var info = info_row.textContent().split(": ", 2);
                if (info.length != 2)
                    continue;
                m.put(info[0], info[1]);
            }
        }

        System.out.println(m);
    }
}
