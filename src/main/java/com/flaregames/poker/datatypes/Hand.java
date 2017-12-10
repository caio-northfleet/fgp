package com.flaregames.poker.datatypes;

import com.flaregames.poker.exceptions.InvalidHandSizeException;

import java.util.Arrays;
import java.util.List;

/**
 * The Poker hand definition.
 *
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 10/12/2017
 */
public final class Hand {

  /**
   * Required number of cards in a the Poker hand.
   */
  private static final int HAND_SIZE = 5;

  /**
   * List of cards in the Poker hand.
   */
  private List<Card> cards;

  /**
   * Hidden class constructor. Initializes internal attributes.
   *
   * @param cards the list of cards in the Poker hand
   */
  private Hand(final List<Card> cards) {
    this.cards = cards;
  }

  /**
   * Build up a {@link Hand} instance of the supplied list of cards.
   *
   * @param cards the list of cards
   * @return the {@link Hand} instance
   * @throws InvalidHandSizeException in case the number of cards do not match the requirements
   */
  public static Hand of(final Card... cards)
      throws InvalidHandSizeException {

    if (cards == null) {
      throw new InvalidHandSizeException("Invalid hand [null].");
    }

    if (cards.length != HAND_SIZE) {
      throw new InvalidHandSizeException(String.format("Invalid hand size [%d].", cards.length));
    }

    return new Hand(Arrays.asList(cards));
  }

  /**
   * List of cards in the Poker hand getter.
   *
   * @return the list of cards in the Poker hand
   */
  public List<Card> getCards() {
    return cards;
  }
}
