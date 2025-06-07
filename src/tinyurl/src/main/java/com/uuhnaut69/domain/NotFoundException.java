package com.uuhnaut69.domain;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {
    super("NOT_FOUND_ERROR");
  }
}
