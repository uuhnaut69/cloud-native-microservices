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

import com.uuhnaut69.catalog.domain.api.CategoryService;
import com.uuhnaut69.catalog.infrastructure.resource.dto.CategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryResource {

  private final CategoryService categoryService;

  @GetMapping
  public Mono<List<CategoryResponse>> getAllCategories() {
    return Mono.fromCompletionStage(categoryService.getAllCategoriesAsync())
        .map(categories -> categories.stream()
            .map(CategoryResponse::fromDomain)
            .toList()
        );
  }
}
