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

import com.uuhnaut69.catalog.domain.PagedResult;
import com.uuhnaut69.catalog.domain.Product;
import com.uuhnaut69.catalog.domain.SearchProductParams;
import com.uuhnaut69.catalog.domain.spi.ProductRepository;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductMongoRepository implements ProductRepository {

  private final ReactiveMongoOperations operations;

  @Override
  public CompletionStage<PagedResult<Product>> searchProductsAsync(SearchProductParams params) {
    final Pageable pageable = PageRequest.of(params.getPageNo(), params.getPageSize());
    final Query searchProductQuery = Query.query(new Criteria());
    final Mono<List<Product>> searchProducts = this.operations.find(
            searchProductQuery.with(pageable),
            ProductEntity.class
        )
        .map(domainMapper)
        .collectList();
    final Mono<Long> countProducts = this.operations.count(
        searchProductQuery,
        ProductEntity.class
    );
    return Mono.zip(searchProducts, countProducts)
        .map(results -> new PagedResult<>(
            results.getT1(),
            results.getT2(),
            params.getPageNo(),
            params.getPageSize())
        ).toFuture();
  }

  private final Function<ProductEntity, Product> domainMapper = entity -> Product.builder()
      .id(entity.getId())
      .createdAt(entity.getCreatedAt())
      .name(entity.getName())
      .imageUrl(entity.getImageUrl())
      .description(entity.getDescription())
      .sku(entity.getSku())
      .price(entity.getPrice())
      .categories(entity.getCategories())
      .build();
}
