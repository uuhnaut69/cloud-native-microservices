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

import com.uuhnaut69.domain.api.TinyUrlService;
import com.uuhnaut69.domain.spi.TinyUrlRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultTinyUrlService implements TinyUrlService {

  private final TinyUrlRepository tinyUrlRepository;

  @Override
  public TinyUrl generateShortUrl(String originalUrl) {
    return this.tinyUrlRepository.save(TinyUrl.of(originalUrl));
  }

  @Override
  public String getOriginalUrlFromShortUrl(String shortUrl) {
    return this.tinyUrlRepository.findByShortUrl(shortUrl).map(TinyUrl::getOriginalUrl)
        .orElseThrow(NotFoundException::new);
  }
}
