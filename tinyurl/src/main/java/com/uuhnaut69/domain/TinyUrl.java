package com.uuhnaut69.domain;

import com.github.f4b6a3.uuid.UuidCreator;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TinyUrl {

  private String id;
  private String originalUrl;
  private String shortUrl;
  private Instant createdAt;

  public static TinyUrl of(String originalUrl) {
    TinyUrl tinyUrl = new TinyUrl();
    tinyUrl.setId(UuidCreator.getTimeOrderedEpoch().toString());
    tinyUrl.setOriginalUrl(originalUrl);
    tinyUrl.setShortUrl(RandomUtils.randomString());
    tinyUrl.setCreatedAt(Instant.now());
    return tinyUrl;
  }
}
