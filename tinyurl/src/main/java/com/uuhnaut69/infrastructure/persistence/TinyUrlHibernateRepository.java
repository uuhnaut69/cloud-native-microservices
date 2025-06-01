package com.uuhnaut69.infrastructure.persistence;

import com.uuhnaut69.domain.TinyUrl;
import com.uuhnaut69.domain.spi.TinyUrlRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class TinyUrlHibernateRepository
    implements TinyUrlRepository, PanacheRepositoryBase<TinyUrlEntity, String> {

  @Override
  public TinyUrl save(TinyUrl tinyUrl) {
    final TinyUrlEntity entity = new TinyUrlEntity(tinyUrl);
    this.persist(entity);
    return tinyUrl;
  }

  @Override
  public Optional<TinyUrl> findByShortUrl(String shortUrl) {
    return this.find("shortUrl", shortUrl).firstResultOptional()
        .map(tinyUrlEntity -> TinyUrl.builder()
            .id(tinyUrlEntity.getId())
            .originalUrl(tinyUrlEntity.getOriginalUrl())
            .shortUrl(tinyUrlEntity.getShortUrl())
            .createdAt(tinyUrlEntity.getCreatedAt())
            .build()
        );
  }
}
