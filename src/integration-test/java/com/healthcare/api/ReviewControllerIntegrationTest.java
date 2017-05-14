package com.healthcare.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.IntegrationTestConfiguration;
import com.healthcare.model.entity.Review;
import com.healthcare.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@AutoConfigureDataJpa
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {IntegrationTestConfiguration.class}
)
public class ReviewControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateReview() throws Exception {
        // given
        final Review review = new Review();
        User user = new User();
        user.setId(2L);
        review.setUser(user);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();

        /*final String body = "{" +
                "\"user\":\"1\"," +
                "\"employee\":\"2\"," +
                "\"user1\":\"3\"," +
                "}";*/
        HttpEntity<String> httpEntity = new HttpEntity<>(
                objectMapper.writeValueAsString(review), headers
        );
        // when
        ResponseEntity<Long> response = restTemplate.exchange("/api/review", HttpMethod.POST, httpEntity, Long.class);
        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    public void testGet() {
        // given
        // when
        ResponseEntity<String> response = restTemplate.getForEntity("/api/review/1", String.class);
        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}