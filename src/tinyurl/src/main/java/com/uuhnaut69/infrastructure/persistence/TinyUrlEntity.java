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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tiny_urls")
public class TinyUrlEntity {

  @Id
  private String id;
  @Column(name = "original_url")
  private String originalUrl;
  @Column(name = "short_url")
  private String shortUrl;
  @Column(name = "created_at")
  private Instant createdAt;

  public TinyUrlEntity(TinyUrl tinyUrl) {
    this.id = tinyUrl.getId();
    this.originalUrl = tinyUrl.getOriginalUrl();
    this.shortUrl = tinyUrl.getShortUrl();
    this.createdAt = tinyUrl.getCreatedAt();
  }
}
