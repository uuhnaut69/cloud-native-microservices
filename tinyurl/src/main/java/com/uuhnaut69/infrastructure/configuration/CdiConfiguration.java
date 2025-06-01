package com.uuhnaut69.infrastructure.configuration;

import com.uuhnaut69.domain.DefaultTinyUrlService;
import com.uuhnaut69.domain.api.TinyUrlService;
import com.uuhnaut69.domain.spi.TinyUrlRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class CdiConfiguration {

  @Produces
  TinyUrlService tinyUrlService(
      TinyUrlRepository tinyUrlRepository
  ) {
    return new DefaultTinyUrlService(tinyUrlRepository);
  }
}
