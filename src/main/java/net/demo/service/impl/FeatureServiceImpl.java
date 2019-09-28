package net.demo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.demo.dto.FeatureDto;
import net.demo.dto.source.FeatureDataDto;
import net.demo.dto.source.FeaturesDataDto;
import net.demo.service.FeatureService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation service for emulating work with features
 */
@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final ObjectMapper objectMapper;

    @Override
    public Optional<FeatureDto> getFeatureById(String id) {
        return getNormalizedData().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<FeatureDto> getFeatures() {
        return getNormalizedData();
    }

    @Override
    public Optional<byte[]> getFeatureImageById(String id) {
        return getFeatureById(id).map(f -> decodeFromBase64(f.getImage()));
    }

    private byte[] decodeFromBase64(String image) {
        return Base64.getDecoder().decode(image);
    }

    private List<FeatureDto> getNormalizedData() {
        return getData().stream()
                .map(data -> mapToDto(data.getFeatures()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<FeatureDto> mapToDto(List<FeatureDataDto> dataSet) {
        return Optional.ofNullable(dataSet).orElse(Collections.emptyList())
                .stream()
                .map(data -> new FeatureDto()
                .setBeginViewingDate(data.getProperties().getAcquisition().getBeginViewingDate())
                .setEndViewingDate(data.getProperties().getAcquisition().getEndViewingDate())
                .setId(data.getProperties().getId())
                .setTimestamp(data.getProperties().getTimestamp())
                .setMissionName(data.getProperties().getAcquisition().getMissionName())
                .setImage(data.getProperties().getQuicklook()))
                .collect(Collectors.toList());

    }

    @SneakyThrows
    private List<FeaturesDataDto> getData() {
        ClassPathResource mockFile = new ClassPathResource("mockdata/source-data.json");
        return objectMapper.readValue(mockFile.getInputStream(),
                new TypeReference<List<FeaturesDataDto>>() { });
    }
}
