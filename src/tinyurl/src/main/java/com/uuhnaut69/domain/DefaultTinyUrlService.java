package com.uuhnaut69.domain;

import com.uuhnaut69.domain.api.TinyUrlService;
import com.uuhnaut69.domain.spi.TinyUrlRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultTinyUrlService implements TinyUrlService {

  private final TinyUrlRepository tinyUrlRepository;

  @Override
  public TinyUrl generateShortUrl(String originalUrl) {
    return this.tinyUrlRepository.save(TinyUrl.of(originalUrl));
  }

  @Override
  public String getOriginalUrlFromShortUrl(String shortUrl) {
    return this.tinyUrlRepository.findByShortUrl(shortUrl).map(TinyUrl::getOriginalUrl)
        .orElseThrow(NotFoundException::new);
  }
}
