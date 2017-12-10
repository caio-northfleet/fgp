package com.flaregames.poker.datatypes;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import com.flaregames.poker.exceptions.InvalidHandSizeException;

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
  public static final int HAND_SIZE = 5;

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
  public static Hand of(final List<Card> cards)
      throws InvalidHandSizeException {

    if (cards == null) {
      throw new InvalidHandSizeException("Invalid hand [null].");
    }

    if (cards.size() != HAND_SIZE) {
      throw new InvalidHandSizeException(String.format("Invalid hand size [%d].", cards.size()));
    }

    return new Hand(cards);
  }

  /**
   * List of cards in the Poker hand getter.
   *
   * @return the list of cards in the Poker hand
   */
  public List<Card> getCards() {
    return cards;
  }

  /**
   * Compares this object instance with another object to check if they are equal.
   *
   * @param obj object to compare
   * @return {@code true} if objects are equal, {@code false} otherwise
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Hand)) {
      return false;
    }
    final Hand hand = (Hand) obj;
    return Objects.equal(getCards(), hand.getCards());
  }

  /**
   * Generates an integer value from this object instance attributes.
   *
   * @return an integer representation of this object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(
        getCards());
  }

  /**
   * Generates a string value from this object instance attributes.
   *
   * @return a string representation of this object
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Hand")
        .add("cards", cards)
        .toString();
  }
}
