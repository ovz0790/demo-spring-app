package net.demo.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.demo.dto.FeatureDto;
import net.demo.exception.FeatureNotFoundException;
import net.demo.service.FeatureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/features", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping
    @ApiOperation("Get all features")
    public List<FeatureDto> getFeatures() {
        return featureService.getFeatures();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get features by id")
    public FeatureDto getFeaturesById(@PathVariable String id) {
        return featureService.getFeatureById(id).orElseThrow(() ->
                new FeatureNotFoundException("Feature with id" + id + "not found"));
    }

    @SneakyThrows
    @GetMapping("/{id}/quicklook")
    @ApiOperation("Get image for the given id")
    public void getFeaturesById(@PathVariable String id, HttpServletResponse response) {
        Optional<byte[]> image = featureService.getFeatureImageById(id);

        if (image.isPresent()) {
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE);
            response.getOutputStream().write(image.get());
        } else {
            throw new FeatureNotFoundException("Feature with id" + id + "not found");
        }
    }
}
