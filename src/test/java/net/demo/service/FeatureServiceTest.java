package net.demo.service;

import net.demo.Integration;
import net.demo.dto.FeatureDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Integration
@RunWith(SpringRunner.class)
public class FeatureServiceTest {
    private static final String ID = "cf5dbe37-ab95-4af1-97ad-2637aec4ddf0";
    private static final String NOT_FOUND_ID = "11111111111111111111111";

    @Autowired
    private FeatureService featureService;


    @Test
    public void shouldGetListOfAllFeatures() {
        List<FeatureDto> features = featureService.getFeatures();
        assertEquals(14, features.size());
    }

    @Test
    public void shouldFindFeatureById() {
        Optional<FeatureDto> feature = featureService.getFeatureById(ID);
        assertEquals(ID, feature.get().getId());
    }

    @Test
    public void shouldNotFindFeatureById() {
        Optional<FeatureDto> featureDto  = featureService.getFeatureById(NOT_FOUND_ID);
        assertFalse(featureDto.isPresent());
    }

    @Test
    public void shouldFindArrayById() {
        Optional<byte[]> image = featureService.getFeatureImageById(ID);
        assertTrue(image.isPresent());
    }

    @Test
    public void shouldNotFindByteArrayById() {
        Optional<byte[]> image = featureService.getFeatureImageById(NOT_FOUND_ID);
        assertFalse(image.isPresent());
    }
}
