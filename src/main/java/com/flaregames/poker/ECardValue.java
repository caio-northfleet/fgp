package com.flaregames.poker;

public enum ECardValue {

  TWO('2', 0),
  THREE('3', 1),
  FOUR('4', 2),
  FIVE('5', 3),
  SIX('6', 4),
  SEVEN('7', 5),
  EIGHT('8', 6),
  NINE('9', 7),
  TEN('T', 8),
  JACK('J', 9),
  QUEEN('Q', 10),
  KING('K', 11),
  ACE('A', 12);

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
