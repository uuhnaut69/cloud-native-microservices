package com.uuhnaut69.infrastructure.resource;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ShortenUrlRequest(
    @NotBlank(message = "ORIGINAL_URL_MUST_NOT_BE_BLANK")
    @URL(message = "ORIGINAL_URL_MUST_BE_VALID_URL")
    String originalUrl
) {

}
