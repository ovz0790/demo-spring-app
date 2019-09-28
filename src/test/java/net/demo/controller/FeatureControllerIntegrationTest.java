package net.demo.controller;

import net.demo.WebIntegration;
import net.demo.dto.FeatureDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@WebIntegration
public class FeatureControllerIntegrationTest {
    private static final String ID = "cf5dbe37-ab95-4af1-97ad-2637aec4ddf0";
    private static final String NOT_FOUND_ID = "11111111111111111111111";
    private static final String FEATURE_URL = "/api/features";
    private static final String FEATURE_BY_ID_URL = "/api/features/%s";
    private static final String FEATURE_QUICK_LOOK_URL = "/api/features/%s/quicklook";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetListOfAllFeatures() {
        ParameterizedTypeReference<List<FeatureDto>> responseType =
                new ParameterizedTypeReference<List<FeatureDto>>() { };
        List<FeatureDto> features = restTemplate.exchange(FEATURE_URL, GET, EMPTY, responseType).getBody();
        assertEquals(14, features.size());
    }

    @Test
    public void shouldFindFeatureById() {
        FeatureDto feature = restTemplate.getForEntity(String.format(FEATURE_BY_ID_URL, ID),
                FeatureDto.class).getBody();
        assertEquals(ID, feature.getId());
    }

    @Test
    public void shouldReturnNotFoundFeatureById() {
        ResponseEntity notFound  = restTemplate.getForEntity(String.format(FEATURE_BY_ID_URL, NOT_FOUND_ID),
                Object.class);
        assertEquals(NOT_FOUND, notFound.getStatusCode());
    }

    @Test
    public void shouldReturnImage() {
        ResponseEntity response  = restTemplate.getForEntity(String.format(FEATURE_QUICK_LOOK_URL , ID),
                byte[].class);
        assertEquals(OK, response.getStatusCode());
        List<String> headers = response.getHeaders().get("Content-type");
        assertTrue(headers.stream().anyMatch(h -> h.contains(MediaType.IMAGE_PNG_VALUE)));
    }
}
