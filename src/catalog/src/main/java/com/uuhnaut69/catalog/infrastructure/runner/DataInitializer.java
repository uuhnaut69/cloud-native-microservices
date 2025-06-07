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

package com.uuhnaut69.catalog.infrastructure.runner;

import com.github.slugify.Slugify;
import com.uuhnaut69.catalog.domain.Product.CategoryValue;
import com.uuhnaut69.catalog.infrastructure.persistence.CategoryEntity;
import com.uuhnaut69.catalog.infrastructure.persistence.ProductEntity;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

  private final ReactiveMongoOperations operations;

  @Override
  public void run(ApplicationArguments args) {
    final Slugify slugify = Slugify.builder().build();
    final Mono<List<CategoryEntity>> seedCategories = operations.exists(
            new Query(), CategoryEntity.class)
        .flatMap(exists -> exists
            ? Mono.empty()
            : this.operations.insertAll(List.of(
                CategoryEntity.of("Phone", slugify.slugify("phone"))
            )).collectList()
        );

    seedCategories.flatMap(categories -> {
      if (CollectionUtils.isEmpty(categories)) {
        return Mono.empty();
      }
      CategoryEntity category = categories.getFirst();
      ProductEntity product = ProductEntity.builder()
          .name("Iphone 15 Pro")
          .sku("IP15P")
          .description("The latest iPhone 15 with advanced features.")
          .price(99900L)
          .imageUrl("https://example.com/iphone15pro.jpg")
          .categories(Set.of(
              new CategoryValue(
                  category.getId(),
                  category.getName(),
                  category.getSlug())
          ))
          .build();
      return operations.insertAll(List.of(product)).collectList();
    }).subscribe();
  }
}
