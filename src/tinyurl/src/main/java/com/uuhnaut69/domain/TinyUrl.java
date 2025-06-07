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

package com.uuhnaut69.domain;

import com.github.f4b6a3.uuid.UuidCreator;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TinyUrl {

  private String id;
  private String originalUrl;
  private String shortUrl;
  private Instant createdAt;

  public static TinyUrl of(String originalUrl) {
    TinyUrl tinyUrl = new TinyUrl();
    tinyUrl.setId(UuidCreator.getTimeOrderedEpoch().toString());
    tinyUrl.setOriginalUrl(originalUrl);
    tinyUrl.setShortUrl(RandomUtils.randomString());
    tinyUrl.setCreatedAt(Instant.now());
    return tinyUrl;
  }
}
