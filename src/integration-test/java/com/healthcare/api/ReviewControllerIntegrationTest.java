package com.healthcare.api;

import com.healthcare.IntegrationTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {IntegrationTestConfiguration.class}
)
public class ReviewControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateReview() {
        // given
        final String body = "{" +
                    "\"user\":\"1\"," +
                    "\"employee\":\"2\"," +
                    "\"user1\":\"3\"," +
                "}";
        HttpEntity<String> httpEntity = new HttpEntity<>(body);
        // when
        ResponseEntity<String> response = restTemplate.exchange("/api/review", HttpMethod.POST, httpEntity, String.class);
        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}