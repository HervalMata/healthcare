package com.healthcare;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@TestPropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.healthcare")
@ActiveProfiles("integration-test")
public class IntegrationTestConfiguration {

}
