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

import com.uuhnaut69.domain.Ad;
import com.uuhnaut69.domain.spi.AdRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

@R2dbcRepository(dialect = Dialect.MYSQL)
public abstract class AdR2DbcRepository
    implements AdRepository, ReactorCrudRepository<AdEntity, UUID> {

  @NotNull
  @Override
  public CompletionStage<List<Ad>> getAllAdsAsync() {
    return this.findAll().map(toDomain()).collectList().toFuture();
  }

  private Function<AdEntity, Ad> toDomain() {
    return entity -> Ad.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .url(entity.getUrl())
        .imageUrl(entity.getImageUrl())
        .buttonText(entity.getButtonText())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
