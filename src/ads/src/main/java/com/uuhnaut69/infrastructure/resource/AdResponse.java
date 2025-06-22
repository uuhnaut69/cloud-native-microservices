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

package com.uuhnaut69.infrastructure.resource;

import com.uuhnaut69.domain.Ad;
import io.micronaut.serde.annotation.Serdeable;
import java.time.Instant;
import java.util.UUID;

@Serdeable
public record AdResponse(
    UUID id,
    String title,
    String description,
    String url,
    String imageUrl,
    String buttonText,
    Instant createdAt
) {

  static AdResponse from(Ad ad) {
    return new AdResponse(
        ad.getId(),
        ad.getTitle(),
        ad.getDescription(),
        ad.getUrl(),
        ad.getImageUrl(),
        ad.getButtonText(),
        ad.getCreatedAt()
    );
  }
}
