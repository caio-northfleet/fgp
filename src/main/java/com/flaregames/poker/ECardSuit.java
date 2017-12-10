package com.flaregames.poker;

public enum ECardSuit {

  CLUBS('C'),
  DIAMONDS('D'),
  HEARTS('H'),
  SPADES('S');

  private char suitId;

  ECardSuit(final char suitId) {
    this.suitId = suitId;
  }

  public char getSuitId() {
    return suitId;
  }
}
