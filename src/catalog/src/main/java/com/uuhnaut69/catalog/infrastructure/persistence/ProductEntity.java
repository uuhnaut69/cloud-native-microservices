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

import com.uuhnaut69.catalog.domain.BaseEntity;
import com.uuhnaut69.catalog.domain.Product.CategoryValue;
import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Document(collection = "products")
public class ProductEntity extends BaseEntity {

  private String name;

  private String imageUrl;

  private String description;

  private String sku;

  private Long price;

  private Set<CategoryValue> categories;

  @Id
  @Override
  public String getId() {
    return super.getId();
  }

  @CreatedDate
  @Override
  public Instant getCreatedAt() {
    return super.getCreatedAt();
  }
}
