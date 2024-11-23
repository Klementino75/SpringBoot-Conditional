package ru.netology.springbootconditional.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.netology.springbootconditional.DevProfile;
import ru.netology.springbootconditional.ProductionProfile;
import ru.netology.springbootconditional.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean  // default
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false", matchIfMissing = true)
    public SystemProfile devProfile() {
        System.out.println("\nThe Bean DevProfile has been successfully created.\n");
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    public SystemProfile prodProfile() {
        System.out.println("\nThe Bean ProductionProfile has been successfully created.\n");
        return new ProductionProfile();
    }
}