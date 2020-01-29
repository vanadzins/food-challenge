package com.andis.foodchallenge.configuration.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("fat-secret")
@Getter
@Setter
public class FatSecretSettings {

    private String key;

    private String secret;
}
