package org.severstal.parser.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQConfig {
    private String hostname;
    private String username;
    private String password;
}
