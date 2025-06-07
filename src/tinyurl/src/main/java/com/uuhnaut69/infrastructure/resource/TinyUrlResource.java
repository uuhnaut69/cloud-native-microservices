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

import com.uuhnaut69.domain.TinyUrl;
import com.uuhnaut69.domain.api.TinyUrlService;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.RestResponse;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class TinyUrlResource {

  private final Validator validator;
  private final TinyUrlService tinyUrlService;

  @RunOnVirtualThread
  @Transactional
  @POST
  @Path("/api/v1/urls")
  public ShortenUrlResponse shortenUrl(ShortenUrlRequest request) {
    final Set<ConstraintViolation<ShortenUrlRequest>> violations = this.validator.validate(request);
    if (!violations.isEmpty()) {
      throw new BadRequestException();
    }

    final TinyUrl tinyUrl = this.tinyUrlService.generateShortUrl(request.originalUrl());
    return ShortenUrlResponse.from(tinyUrl);
  }

  @RunOnVirtualThread
  @GET
  @Path("/{shortUrl}")
  public RestResponse<String> getOriginalUrlFromShortUrl(@PathParam("shortUrl") String shortUrl) {
    final String originalUrl = this.tinyUrlService.getOriginalUrlFromShortUrl(shortUrl);
    return RestResponse.seeOther(UriBuilder.fromUri(originalUrl).build());
  }
}
