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

package com.uuhnaut69.infrastructure.runner;

import com.uuhnaut69.infrastructure.persistence.AdEntity;
import com.uuhnaut69.infrastructure.persistence.AdR2DbcRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class DataInitializer {

  private final AdR2DbcRepository repository;

  @EventListener
  void onStartup(StartupEvent event) {
    this.repository.count()
        .filter(count -> count == 0)
        .flatMapMany(count -> this.repository.saveAll(
            List.of(
                AdEntity.builder()
                    .title("Flash Sale - 50% Off!")
                    .description("Get up to 50% off on selected items. Limited time offer!")
                    .buttonText("Shop Now")
                    .url("/flash-sale")
                    .build(),
                AdEntity.builder()
                    .title("New Arrivals - 20% Off!")
                    .description(
                        "Discover our latest collection and enjoy 20% off your first order.")
                    .buttonText("Explore Now")
                    .url("/new-arrivals")
                    .build()
            )
        ))
        .subscribe();
  }
}
