package com.flaregames.poker.evaluator;

import com.flaregames.poker.enums.ECardValue;
import com.flaregames.poker.enums.EHandOutcome;

import java.util.Arrays;
import java.util.List;

public final class HandEvaluatorResult implements Comparable<HandEvaluatorResult> {

  private EHandOutcome handOutcome;
  private List<ECardValue> tieDecreasingComparisonCards;

  private HandEvaluatorResult() {
  }

  static HandEvaluatorResult from(final EHandOutcome handOutcome, final ECardValue... cardValues) {

    final HandEvaluatorResult handEvaluatorResult = new HandEvaluatorResult();
    handEvaluatorResult.handOutcome = handOutcome;
    handEvaluatorResult.tieDecreasingComparisonCards = Arrays.asList(cardValues);
    return handEvaluatorResult;
  }

  public EHandOutcome getHandOutcome() {
    return handOutcome;
  }

  public List<ECardValue> getTieDecreasingComparisonCards() {
    return tieDecreasingComparisonCards;
  }

  @Override
  public String toString() {
    return handOutcome.toString();
  }

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
    return tieComparison(this.getHandOutcome(), this.getTieDecreasingComparisonCards(),
        other.getTieDecreasingComparisonCards());
  }

  private int tieComparison(final EHandOutcome tieOutcome, final List<ECardValue> values1,
                            final List<ECardValue> values2) {

    switch (tieOutcome) {
      case HIGH_CARD:
        break;
      case PAIR:
        break;
      case TWO_PAIRS:
        break;
      case THREE_OF_A_KIND:
        break;
      case STRAIGHT:
        break;
      case FLUSH:
        break;
      case FULL_HOUSE:
        break;
      case FOUR_OF_A_KIND:
        break;
      case STRAIGHT_FLUSH:
        break;
      default:
        break;
    }

    return 0;
  }
}
