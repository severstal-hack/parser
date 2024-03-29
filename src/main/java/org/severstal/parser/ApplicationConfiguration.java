package org.severstal.parser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import org.severstal.parser.worker.TenderWorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class ApplicationConfiguration {
    @Bean
    @Scope("singleton")
    public TenderWorkerFactory tenderWorkerFactory() {
        return new TenderWorkerFactory();
    }
}
