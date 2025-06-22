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

import com.uuhnaut69.domain.BaseEntity;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
@MappedEntity(value = "ads")
public class AdEntity extends BaseEntity {

  private String title;

  private String description;

  private String url;

  private String imageUrl;

  private String buttonText;

  @Id
  @AutoPopulated
  @Override
  public UUID getId() {
    return super.getId();
  }

  @DateCreated
  @Override
  public Instant getCreatedAt() {
    return super.getCreatedAt();
  }

  @DateUpdated
  @Override
  public Instant getUpdatedAt() {
    return super.getUpdatedAt();
  }

}
