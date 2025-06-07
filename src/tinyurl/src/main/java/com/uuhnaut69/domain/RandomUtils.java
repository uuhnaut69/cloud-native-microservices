package com.uuhnaut69.domain;

import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtils {

  private static final int STRING_LENGTH = 8;
  private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  public static String randomString() {
    return NanoIdUtils.randomNanoId(ALPHABET, STRING_LENGTH);
  }
}
