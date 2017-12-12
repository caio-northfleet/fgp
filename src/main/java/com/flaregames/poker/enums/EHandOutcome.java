package com.flaregames.poker.enums;

import com.google.common.base.Ascii;

public enum EHandOutcome {

  HIGH_CARD(1),
  PAIR(2),
  TWO_PAIRS(3),
  THREE_OF_A_KIND(4),
  STRAIGHT(5),
  FLUSH(6),
  FULL_HOUSE(7),
  FOUR_OF_A_KIND(8),
  STRAIGHT_FLUSH(9);

  private int weight;

  EHandOutcome(final int weight) {
    this.weight = weight;
  }

  public int getWeight() {
    return weight;
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
