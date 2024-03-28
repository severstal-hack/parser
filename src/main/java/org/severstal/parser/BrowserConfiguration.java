package org.severstal.parser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class BrowserConfiguration {
    @Bean
    public Browser browser() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        return browser;
    }
}
