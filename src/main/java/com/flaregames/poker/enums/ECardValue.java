package com.flaregames.poker.enums;

/**
 * The card value definition.
 *
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 10/12/2017
 */
public enum ECardValue {

  TWO('2', 1),
  THREE('3', 2),
  FOUR('4', 3),
  FIVE('5', 4),
  SIX('6', 5),
  SEVEN('7', 6),
  EIGHT('8', 7),
  NINE('9', 8),
  TEN('T', 9),
  JACK('J', 10),
  QUEEN('Q', 11),
  KING('K', 12),
  ACE('A', 13);

  private char cardId;
  private int cardValue;

  ECardValue(final char cardId, final int cardValue) {
    this.cardId = cardId;
    this.cardValue = cardValue;
  }

  public char getCardId() {
    return cardId;
  }

  public int getCardValue() {
    return cardValue;
  }
}
