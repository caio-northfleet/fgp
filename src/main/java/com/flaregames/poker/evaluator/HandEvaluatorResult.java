package com.flaregames.poker.evaluator;

import com.flaregames.poker.enums.ECardValue;
import com.flaregames.poker.enums.EHandOutcome;

public final class HandEvaluatorResult implements Comparable<HandEvaluatorResult> {

  private EHandOutcome handOutcome;

  private HandEvaluatorResult() {
  }

  public static HandEvaluatorResult from(final EHandOutcome handOutcome, ECardValue... cardValues) {

    final HandEvaluatorResult handEvaluatorResult = new HandEvaluatorResult();
    handEvaluatorResult.handOutcome = handOutcome;

    return handEvaluatorResult;
  }

  public EHandOutcome getHandOutcome() {
    return handOutcome;
  }

  @Override
  public String toString() {

    return handOutcome.toString();
  }

  @Override
  public int compareTo(final HandEvaluatorResult other) {

    if (this.getHandOutcome().compareTo(other.getHandOutcome()) > 0) {
      return 1;
    }

    if (this.getHandOutcome().compareTo(other.getHandOutcome()) < 0) {
      return -1;
    }


    return 0;
  }
}
