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

import com.uuhnaut69.domain.api.AdService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller("/api")
public class AdResource {

  private final AdService adService;

  @Get(uri = "/v1/ads", produces = "application/json")
  @Operation(
      summary = "Get all ads",
      description = "Get all ads, may return empty list if no ads are available."
  )
  public Mono<List<AdResponse>> getAds() {
    return Mono.fromCompletionStage(this.adService.getAllAdsAsync())
        .map(ads -> ads.stream().map(AdResponse::from).toList());
  }
}
