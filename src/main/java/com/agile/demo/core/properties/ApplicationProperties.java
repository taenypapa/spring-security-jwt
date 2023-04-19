package com.agile.demo.core.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom.properties")
@Getter @Setter
public class ApplicationProperties {
    private String clientId;
    private String clientSecret;
}
