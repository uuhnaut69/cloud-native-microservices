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

package com.uuhnaut69.catalog.infrastructure.persistence;

import com.uuhnaut69.catalog.domain.Category;
import com.uuhnaut69.catalog.domain.spi.CategoryRepository;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryMongoRepository implements CategoryRepository {

  private final ReactiveMongoOperations operations;

  @Override
  public CompletionStage<List<Category>> getAllCategoriesAsync() {
    return this.operations.findAll(CategoryEntity.class).map(domainConverter).collectList()
        .toFuture();
  }

  private final Function<CategoryEntity, Category> domainConverter = entity -> Category.builder()
      .id(entity.getId())
      .name(entity.getName())
      .slug(entity.getSlug())
      .createdAt(entity.getCreatedAt())
      .build();
}
