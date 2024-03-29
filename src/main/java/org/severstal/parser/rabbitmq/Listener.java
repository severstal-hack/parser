package org.severstal.parser.rabbitmq;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQImpl;
import lombok.extern.slf4j.Slf4j;
import org.severstal.parser.domain.Tender;
import org.severstal.parser.domain.TenderItem;
import org.severstal.parser.worker.TatneftWorker;
import org.severstal.parser.worker.TenderProWorker;
import org.severstal.parser.worker.TenderWorker;
import org.severstal.parser.worker.TenderWorkerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j(topic = "rmq.listener")
@Component
public class Listener {
    @Autowired
    private RabbitTemplate template;
    private final TenderWorkerFactory tenderWorkerFactory;

    public Listener(@Autowired TenderWorkerFactory tenderWorkerFactory) {
        this.tenderWorkerFactory = tenderWorkerFactory;
    }

    @RabbitListener(queues = "p-queue")
    public void worker(String message) throws Exception {
        Thread th = new Thread(() -> {
            log.info("received new RMQ message");
            log.info(message);

            Gson gson = new Gson();
            List<TenderItem> items = List.of(gson.fromJson(message, TenderItem[].class));
            log.info("received {} items", items.size());

            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch();

            for (TenderItem item : items) {
                log.info("trying to parse item {} {}", item.getDomain(), item.getLink());
                TenderWorker tenderWorker = this.tenderWorkerFactory.getWorker(item.getDomain(), item.getLink(), browser);
                Tender tender = tenderWorker.run();

                var json = tender.getJSON();
                log.info("parsed item {}", json);

                this.template.setExchange("data-exchange");
                this.template.convertAndSend(json);
            }
        });
        th.start();
        th.join();
    }
}
