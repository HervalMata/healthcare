package com.healthcare;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@EnableAutoConfiguration
@TestPropertySource("classpath:application.properties")
public class IntegrationTestConfiguration {

}
