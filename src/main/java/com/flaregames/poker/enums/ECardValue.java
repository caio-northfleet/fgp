package com.flaregames.poker.enums;

/**
 * The card value definition.
 *
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 10/12/2017
 */
public enum ECardValue {

  TWO('2', 2),
  THREE('3', 3),
  FOUR('4', 4),
  FIVE('5', 5),
  SIX('6', 6),
  SEVEN('7', 7),
  EIGHT('8', 8),
  NINE('9', 9),
  TEN('T', 10),
  JACK('J', 11),
  QUEEN('Q', 12),
  KING('K', 13),
  ACE('A', 14);

  /**
   * The card identifier.
   */
  private char cardId;

  /**
   * The card value.
   */
  private int cardValue;

  /**
   * Enum constructor.
   *
   * @param cardId    the card identifier
   * @param cardValue the card value
   */
  ECardValue(final char cardId, final int cardValue) {
    this.cardId = cardId;
    this.cardValue = cardValue;
  }

  /**
   * The card identifier getter.
   *
   * @return the card identifier
   */
  public char getCardId() {
    return cardId;
  }

  /**
   * The card value getter.
   *
   * @return the card value
   */
  public int getCardValue() {
    return cardValue;
  }
}
