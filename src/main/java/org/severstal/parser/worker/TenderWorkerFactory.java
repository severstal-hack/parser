package org.severstal.parser.worker;

import com.microsoft.playwright.Browser;
import org.springframework.stereotype.Component;

@Component
public class TenderWorkerFactory {
    public TenderWorker getWorker(String domain, String link, Browser browser) {
        switch (domain) {
            case "tender.pro":
                return new TenderProWorker(link, browser);
            case "etp.tatneft.ru":
                return new TatneftWorker(link, browser);
            case "etp-ets.ru":
                var split = link.split("/");
                String type = split[3];
                if (type.equals("44"))
                    split[5] = "items";
                else {
                    split[7] = "lot";
                    split[8] = "view";
                }
                var newLink = String.join("/", split);
                return new EtpEtsWorker(newLink, browser, type);
            default:
                return null;
        }
    }
}
