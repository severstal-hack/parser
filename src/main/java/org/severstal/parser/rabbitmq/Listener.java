package org.severstal.parser.rabbitmq;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQImpl;
import lombok.extern.slf4j.Slf4j;
import org.severstal.parser.domain.TenderItem;
import org.severstal.parser.worker.TatneftWorker;
import org.severstal.parser.worker.TenderProWorker;
import org.severstal.parser.worker.TenderWorker;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Listener {
    private final Browser browser;

    public Listener(@Autowired Browser browser) {
        this.browser = browser;
    }

    @RabbitListener(queues = "p-queue")
    public void worker(String message) throws Exception {
        Thread th = new Thread(() -> {
            System.out.println("test");
            Gson gson = new Gson();
            TenderItem item = gson.fromJson(message, TenderItem.class);
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch();
            TenderWorker tenderWorker = switch (item.getDomain()) {
                case "tender.pro" -> new TenderProWorker(item.getLink(), browser);
                case "etp.tatneft.ru" -> new TatneftWorker(item.getLink(), browser);
                case "etp-ets.ru" -> new TatneftWorker(item.getLink(), browser);
                default -> throw new IllegalStateException("Unexpected value: " + item.getDomain());
            };
            tenderWorker.run();
        });
        th.start();
        th.join();
    }
}
