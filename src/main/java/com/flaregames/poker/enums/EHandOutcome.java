package com.flaregames.poker.enums;

import com.google.common.base.Ascii;

public enum EHandOutcome {

  HIGH_CARD(5),
  PAIR(4),
  TWO_PAIRS(3),
  THREE_OF_A_KIND(1),
  STRAIGHT(1),
  FLUSH(5),
  FULL_HOUSE(1),
  FOUR_OF_A_KIND(1),
  STRAIGHT_FLUSH(1);

  private int requiredComparisonCards;

  EHandOutcome(final int requiredComparisonCards) {
    this.requiredComparisonCards = requiredComparisonCards;
  }

  public int getRequiredComparisonCards() {
    return requiredComparisonCards;
  }

  @Override
  public String toString() {

    final char[] original = this.name().toCharArray();

    final StringBuilder out = new StringBuilder(original.length);
    for (final char part : original) {
      out.append(part == '_' ? ' ' : Ascii.toLowerCase(part));
    }

    return out.toString();
  }
}
