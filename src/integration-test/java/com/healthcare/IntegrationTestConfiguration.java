package com.healthcare;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@Configuration
@WebAppConfiguration
@EnableAutoConfiguration
@TestPropertySource("classpath:application.properties")
public class IntegrationTestConfiguration {

}
