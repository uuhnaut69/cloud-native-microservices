package com.uuhnaut69.domain.api;

import com.uuhnaut69.domain.TinyUrl;

public interface TinyUrlService {

  TinyUrl generateShortUrl(String originalUrl);

  String getOriginalUrlFromShortUrl(String shortUrl);
}
