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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j(topic = "rmq.listener")
@Component
public class Listener {
    private final TenderWorkerFactory tenderWorkerFactory;

    public Listener(@Autowired TenderWorkerFactory tenderWorkerFactory) {
        this.tenderWorkerFactory = tenderWorkerFactory;
    }

    @RabbitListener(queues = "p-queue")
    public void worker(String message) throws Exception {
        Thread th = new Thread(() -> {
            log.debug("Received new RMQ message");
            Gson gson = new Gson();
            TenderItem item = gson.fromJson(message, TenderItem.class);
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch();
            TenderWorker tenderWorker = this.tenderWorkerFactory.getWorker(item.getDomain(), item.getLink(), browser);
            Tender tender = tenderWorker.run();
            System.out.println(tender.getJSON());
        });
        th.start();
        th.join();
    }
}
