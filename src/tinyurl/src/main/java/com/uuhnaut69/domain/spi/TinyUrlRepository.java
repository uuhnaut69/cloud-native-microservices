package com.uuhnaut69.domain.spi;

import com.uuhnaut69.domain.TinyUrl;
import java.util.Optional;

public interface TinyUrlRepository {

  TinyUrl save(TinyUrl tinyUrl);

  Optional<TinyUrl> findByShortUrl(String shortUrl);
}
