package com.flaregames.poker.evaluator;

import com.google.common.base.Objects;

import com.flaregames.poker.enums.ECardValue;
import com.flaregames.poker.enums.EHandOutcome;
import com.flaregames.poker.exceptions.InvalidOutcomeCompositionException;

import java.util.Arrays;
import java.util.List;

public final class HandEvaluatorResult implements Comparable<HandEvaluatorResult> {

  /**
   * The hand evaluation outcome.
   */
  private EHandOutcome handOutcome;

  /**
   * The list of cards used for tie comparison.
   */
  private List<ECardValue> tieDecreasingComparisonCards;

  /**
   * Hidden class constructor.
   */
  private HandEvaluatorResult() {
  }

  /**
   * Build up a {@link HandEvaluatorResult} instance of the supplied hand outcome and tie
   * comparison cards.
   *
   * @param handOutcome the hand evaluation outcome
   * @param cardValues  the list of tie comparison cards
   * @return the {@link HandEvaluatorResult} instance
   * @throws InvalidOutcomeCompositionException in case the arguments contain inconsistent data
   */
  static HandEvaluatorResult from(final EHandOutcome handOutcome, final ECardValue... cardValues)
      throws InvalidOutcomeCompositionException {

    if (cardValues.length != handOutcome.getRequiredComparisonCards()) {
      throw new InvalidOutcomeCompositionException(
          String.format("Hand outcome [%s] requires [%d] cards to be correctly compared.",
              handOutcome, handOutcome.getRequiredComparisonCards()));
    }

    final HandEvaluatorResult handEvaluatorResult = new HandEvaluatorResult();
    handEvaluatorResult.handOutcome = handOutcome;
    handEvaluatorResult.tieDecreasingComparisonCards = Arrays.asList(cardValues);

    return handEvaluatorResult;
  }

  /**
   * The hand evaluation outcome getter.
   *
   * @return the hand evaluation outcome
   */
  public EHandOutcome getHandOutcome() {
    return handOutcome;
  }

  /**
   * The tie comparison cards getter.
   *
   * @return the tie comparison cards
   */
  private List<ECardValue> getTieDecreasingComparisonCards() {
    return tieDecreasingComparisonCards;
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
    if (!(obj instanceof HandEvaluatorResult)) {
      return false;
    }
    final HandEvaluatorResult that = (HandEvaluatorResult) obj;
    return Objects.equal(getHandOutcome(), that.getHandOutcome())
        && Objects.equal(getTieDecreasingComparisonCards(), that.getTieDecreasingComparisonCards());
  }

  /**
   * Generates an integer value from this object instance attributes.
   *
   * @return an integer representation of this object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(
        getHandOutcome(),
        getTieDecreasingComparisonCards());
  }

  /**
   * Generates a string representing this object.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return handOutcome.toString();
  }

  /**
   * Compares this object with the specified object for order.
   *
   * @param other the object to be compared
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   *     or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   */
  @Override
  public int compareTo(final HandEvaluatorResult other) {
    if (other == null) {
      throw new NullPointerException();
    }

    if (this.getHandOutcome().compareTo(other.getHandOutcome()) > 0) {
      return 1;
    }
    if (this.getHandOutcome().compareTo(other.getHandOutcome()) < 0) {
      return -1;
    }

    for (int i = 0; i < this.getHandOutcome().getRequiredComparisonCards(); i++) {
      final int cardComparisonResult = this.getTieDecreasingComparisonCards().get(i)
          .compareTo(other.getTieDecreasingComparisonCards().get(i));
      if (cardComparisonResult != 0) {
        return cardComparisonResult;
      }
    }

    return 0;
  }
}
