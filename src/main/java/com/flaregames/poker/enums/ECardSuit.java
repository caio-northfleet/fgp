package com.flaregames.poker.enums;

/**
 * The card suit definition.
 *
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 10/12/2017
 */
public enum ECardSuit {

  CLUBS('C'),
  DIAMONDS('D'),
  HEARTS('H'),
  SPADES('S');

  /**
   * The card suit identifier.
   */
  private char suitId;

  /**
   * Enum constructor.
   *
   * @param suitId the card suit identifier.
   */
  ECardSuit(final char suitId) {
    this.suitId = suitId;
  }

  /**
   * The card suit identifier getter.
   *
   * @return the card suit identifier
   */
  public char getSuitId() {
    return suitId;
  }
}
