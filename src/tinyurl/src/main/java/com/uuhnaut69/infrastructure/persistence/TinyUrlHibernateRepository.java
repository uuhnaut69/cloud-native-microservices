/*
 * Copyright 2025 uuhnaut69
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
