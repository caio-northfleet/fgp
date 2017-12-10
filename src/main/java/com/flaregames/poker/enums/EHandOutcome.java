package com.flaregames.poker.enums;

import com.google.common.base.Ascii;

public enum EHandOutcome {

  HIGH_CARD(1),
  PAIR(10),
  TWO_PAIRS(20),
  THREE_OF_A_KIND(30),
  STRAIGHT(40),
  FLUSH(50),
  FULL_HOUSE(60),
  FOUR_OF_A_KIND(70),
  STRAIGHT_FLUSH(80);

  private int multiplier;

  EHandOutcome(final int multiplier) {
    this.multiplier = multiplier;
  }

  public int getMultiplier() {
    return multiplier;
  }

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
