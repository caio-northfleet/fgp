package com.flaregames.poker.datatypes;

import com.flaregames.poker.enums.ECardSuit;
import com.flaregames.poker.enums.ECardValue;
import com.flaregames.poker.exceptions.InvalidCardInputException;

/**
 * The Poker hand card definition.
 *
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @see ECardSuit
 * @see ECardValue
 * @since 10/12/2017
 */
public final class Card {

  /**
   * The card suit.
   */
  private ECardSuit suit;

  /**
   * The card value.
   */
  private ECardValue value;

  /**
   * Hidden class constructor.
   */
  private Card() {
  }

  /**
   * Build up a {@link Card} instance of the supplied raw card input.
   *
   * @param rawCardInput the raw card input
   * @return the {@link Card} instance
   * @throws InvalidCardInputException in case the raw card input contains invalid data
   */
  public static Card from(final String rawCardInput)
      throws InvalidCardInputException {

    if (rawCardInput == null || rawCardInput.length() != 2) {
      throw new InvalidCardInputException(String.format("Invalid input [%s].", rawCardInput));
    }

    final Card card = new Card();

    final char rawSuitId = rawCardInput.charAt(0);
    for (final ECardSuit suit : ECardSuit.values()) {
      if (suit.getSuitId() == rawSuitId) {
        card.suit = suit;
        break;
      }
    }
    if (card.suit == null) {
      throw new InvalidCardInputException(String.format("Invalid card suit [%s].", rawSuitId));
    }

    final char rawCardId = rawCardInput.charAt(1);
    for (final ECardValue value : ECardValue.values()) {
      if (value.getCardId() == rawCardId) {
        card.value = value;
        break;
      }
    }
    if (card.value == null) {
      throw new InvalidCardInputException(String.format("Invalid card value [%s].", rawCardId));
    }

    return card;
  }

  /**
   * The card suit getter.
   *
   * @return the card suit
   */
  public ECardSuit getSuit() {
    return suit;
  }

  /**
   * The card value getter.
   *
   * @return the card value
   */
  public ECardValue getValue() {
    return value;
  }
}
