package org.severstal.parser;

import org.severstal.parser.rabbitmq.RabbitMQConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitMQConfiguration.class)
public class ParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner CommandLineRunnerBean() {
//
//        Playwright playwright = Playwright.create();
//        Browser browser = playwright.chromium().launch();
//        return (args) -> {
//            ApplicationConfig cfg = new ApplicationConfig();
//            ManagedChannel channel = ManagedChannelBuilder
//                    .forTarget(String.format("%s:%d", cfg.getGrpcHost(), cfg.getGrpcPort()))
//                    .usePlaintext()
//                    .build();
//            DataServiceGrpc.DataServiceStub stub = DataServiceGrpc.newStub(channel);
//            StreamObserver<DataServiceOuterClass.AddParsedDataRequest> obs = stub.addParsedData(new StreamObserver<DataServiceOuterClass.AddParsedDataResponse>() {
//                @Override
//                public void onNext(DataServiceOuterClass.AddParsedDataResponse addParsedDataResponse) {
//
//                }
//
//                @Override
//                public void onError(Throwable throwable) {
//
//                }
//
//                @Override
//                public void onCompleted() {
//
//                }
//            });
//
////            TenderWorker worker = new TenderProWorker("https://www.tender.pro/api/tender/580541/view_public", browser);
////            Tender tender = worker.run();
////            System.out.println(tender.getJSON());
//////            TatneftWorker worker = new TatneftWorker("https://etp.tatneft.ru/pls/tzp/f?p=220:2155:9588354296678::::P2155_REQ_ID,P2155_PREV_PAGE:3394532180021,562", browser);
//////            Tender tender = worker.run();
//        };
//    }
}
