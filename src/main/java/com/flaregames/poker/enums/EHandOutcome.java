package com.flaregames.poker.enums;

import com.google.common.base.Ascii;

/**
 * The hand outcome definition.
 *
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 13/12/2017
 */
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

  /**
   * The number of cards required for comparing tie situations between two Poker hands.
   */
  private int requiredComparisonCards;

  /**
   * Enum constructor.
   *
   * @param requiredComparisonCards the comparison required number of cards
   */
  EHandOutcome(final int requiredComparisonCards) {
    this.requiredComparisonCards = requiredComparisonCards;
  }

  /**
   * The comparison required number of cards getter.
   *
   * @return the comparison required number of cards
   */
  public int getRequiredComparisonCards() {
    return requiredComparisonCards;
  }

  /**
   * Generates a string representing this enumeration value.
   *
   * @return the string representation of the enumeration value
   */
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
