package com.uuhnaut69.infrastructure.resource;

import com.uuhnaut69.domain.TinyUrl;

public record ShortenUrlResponse(
    String id,
    String originalUrl,
    String shortUrl,
    String createdAt
) {

  static ShortenUrlResponse from(TinyUrl tinyUrl) {
    return new ShortenUrlResponse(
        tinyUrl.getId(),
        tinyUrl.getOriginalUrl(),
        tinyUrl.getShortUrl(),
        tinyUrl.getCreatedAt().toString()
    );
  }
}
