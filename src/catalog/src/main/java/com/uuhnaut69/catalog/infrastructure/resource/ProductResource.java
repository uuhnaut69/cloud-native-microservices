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

package com.uuhnaut69.catalog.infrastructure.resource;

import com.uuhnaut69.catalog.domain.PagedResult;
import com.uuhnaut69.catalog.domain.SearchProductParams;
import com.uuhnaut69.catalog.domain.api.ProductService;
import com.uuhnaut69.catalog.infrastructure.resource.dto.ProductResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductResource {

  private final ProductService productService;

  @Validated
  @GetMapping
  public Mono<PagedResult<ProductResponse>> searchProducts(
      @RequestParam(value = "page_no", defaultValue = "0") int pageNo,
      @RequestParam(value = "page_size", defaultValue = "10") int pageSize
  ) {
    final SearchProductParams params = SearchProductParams.builder()
        .pageNo(pageNo)
        .pageSize(pageSize)
        .build();
    return Mono.fromCompletionStage(this.productService.searchProductsAsync(params))
        .map(result -> {
          final List<ProductResponse> items = result.getItems()
              .stream().map(ProductResponse::fromDomain).toList();
          return new PagedResult<>(
              items,
              result.getTotalItems(),
              result.getPageNo(),
              result.getPageSize()
          );
        });
  }
}
