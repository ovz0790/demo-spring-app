package net.demo.service;

import net.demo.dto.FeatureDto;

import java.util.List;
import java.util.Optional;

/**
 * Service for emulating work with features
 */
public interface FeatureService {

    /**
     * Get feature
     * @param id feature id
     * @return feature
     */
    Optional<FeatureDto> getFeatureById(String id);

    /**
     * Get all features list
     * @return features
     */
    List<FeatureDto> getFeatures();

    /**
     * Get image of feature
     * @param id feature id
     * @return bytes of image
     */
    Optional<byte[]> getFeatureImageById(String id);

}
