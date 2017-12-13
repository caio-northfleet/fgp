package com.flaregames.poker.enums;

import com.google.common.base.Ascii;

public enum EHandOutcome {

  HIGH_CARD,
  PAIR,
  TWO_PAIRS,
  THREE_OF_A_KIND,
  STRAIGHT,
  FLUSH,
  FULL_HOUSE,
  FOUR_OF_A_KIND,
  STRAIGHT_FLUSH;

  @Override
  public String toString() {

    final char[] original = this.name().toCharArray();

    final StringBuilder out = new StringBuilder(original.length);
    out.append(original[0]);
    for (int i = 1; i < original.length; i++) {
      out.append(original[i] == '_' ? ' ' : Ascii.toLowerCase(original[i]));
    }

    return out.toString();
  }
}
