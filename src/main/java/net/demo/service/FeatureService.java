package net.demo.service;

import net.demo.dto.FeatureDto;

import java.util.List;
import java.util.Optional;

/**
 * Service for emulating work with features
 */
public interface FeatureService {

    Optional<FeatureDto> getFeatureById(String id);

    List<FeatureDto> getFeatures();

    Optional<byte[]> getFeatureImageById(String id);

}
