package com.healthcare.api;

import com.healthcare.IntegrationTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {IntegrationTestConfiguration.class}
)
public class ReviewControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {

    }

    // TODO: finish tests
    @Test
    public void testGetReview() {
        // given
        // when
        ResponseEntity<Long> result = restTemplate.getForEntity("/api/review/1", Long.class);
        // then
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
    }

}