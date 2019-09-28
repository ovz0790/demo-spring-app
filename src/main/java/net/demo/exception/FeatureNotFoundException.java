package net.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Feature not found")
public class FeatureNotFoundException extends RuntimeException {

    public FeatureNotFoundException(String message) {
        super(message);
    }

    public FeatureNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
