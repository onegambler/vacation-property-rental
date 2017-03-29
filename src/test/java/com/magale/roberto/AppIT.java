package com.magale.roberto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.magale.roberto.model.PropertyListing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AppIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenBodyIsValidThenAddWorksAsExpected() throws IOException {
        String requestBody = Resources.toString(getResource("message.json"), Charset.defaultCharset());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<PropertyListing> responseEntity =
                restTemplate.exchange("/listings", POST, entity, PropertyListing.class);
        PropertyListing response = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(CREATED);
        assertThat(response).isNotNull();
        assertThat(response.getListing()).isNotNull();
        assertThat(response.getListing().getId()).isNotNull();

        PropertyListing expectedPropertyList = objectMapper.readValue(requestBody, PropertyListing.class);
        assertThat(response.getListing().getLocation()).isEqualTo(expectedPropertyList.getListing().getLocation());
        assertThat(response.getListing().getAddress()).isEqualTo(expectedPropertyList.getListing().getAddress());
        assertThat(response.getListing().getContact()).isEqualTo(expectedPropertyList.getListing().getContact());
    }
}
