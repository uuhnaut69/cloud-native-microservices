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
