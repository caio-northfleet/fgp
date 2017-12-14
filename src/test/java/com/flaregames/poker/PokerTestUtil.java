package com.flaregames.poker;

import com.flaregames.poker.enums.EHandOutcome;
import com.flaregames.poker.exceptions.EvaluateHandException;
import com.flaregames.poker.exceptions.InvalidCardInputException;

import org.assertj.core.api.Assertions;

/**
 * @author Caio Northfleet (caio.northfleet@aquiris.com.br)
 * @since 13/12/2017
 */
public final class PokerTestUtil {

  void compareThreeOfAKind()
      throws InvalidCardInputException, EvaluateHandException {

    String hand1 = "CA HA DA D4 C6";
    String hand2 = "DK D4 CK D3 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.THREE_OF_A_KIND));

    hand1 = "CA HA DA D4 C6";
    hand2 = "DK CK HK D3 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.THREE_OF_A_KIND));
  }

  void compareTwoPairs()
      throws InvalidCardInputException, EvaluateHandException {

    String hand1 = "CA C4 DA D4 C6";
    String hand2 = "DK D4 CK D3 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.TWO_PAIRS));

    hand1 = "CA C4 DA D4 C6";
    hand2 = "DK D4 CK D4 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.TWO_PAIRS));

    hand1 = "CA C4 DA D4 C6";
    hand2 = "DA D4 CA D4 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(tie(EHandOutcome.TWO_PAIRS));

    hand1 = "CA C4 DA D4 C6";
    hand2 = "DA D4 CA D4 D7";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(secondWins(EHandOutcome.TWO_PAIRS));
  }

  void comparePair()
      throws InvalidCardInputException, EvaluateHandException {

    String hand1 = "CA C4 DA C3 C6";
    String hand2 = "DK D4 C2 D3 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.PAIR));

    hand1 = "CA C4 DA C3 C6";
    hand2 = "DK D4 CK D3 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.PAIR));

    hand1 = "CA C4 DA C3 C6";
    hand2 = "DA D4 CA D3 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(tie(EHandOutcome.PAIR));

    hand1 = "CA C4 DA C3 C6";
    hand2 = "DA D4 CA D3 D7";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(secondWins(EHandOutcome.PAIR));
  }

  void compareHighCard()
      throws InvalidCardInputException, EvaluateHandException {

    final String hand1 = "CA C4 D2 C3 C6";
    final String hand2 = "DK D4 C2 D3 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.HIGH_CARD));
  }

  void compareFlush()
      throws InvalidCardInputException, EvaluateHandException {

    String hand1 = "CA C4 C2 C3 C7";
    String hand2 = "DA D3 D5 H8 S8";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.FLUSH));

    hand1 = "CA C4 C2 C3 C6";
    hand2 = "DK D3 D5 D2 D4";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.FLUSH));

    hand1 = "CA C4 C2 C3 C6";
    hand2 = "DA D4 D2 D3 D6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(tie(EHandOutcome.FLUSH));
  }

  void compareStraightFlush()
      throws InvalidCardInputException, EvaluateHandException {

    String hand1 = "CA C4 C2 C3 C5";
    String hand2 = "DA D3 D5 H8 S8";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.STRAIGHT_FLUSH));

    hand1 = "CA C4 C2 C3 C5";
    hand2 = "DA D3 D5 H2 S4";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.STRAIGHT_FLUSH));

    hand1 = "CA C4 C2 C3 C5";
    hand2 = "DA D4 D2 D3 D5";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(tie(EHandOutcome.STRAIGHT_FLUSH));

    hand1 = "CA C4 C2 C3 C5";
    hand2 = "D6 D7 D8 D9 DT";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(secondWins(EHandOutcome.STRAIGHT_FLUSH));
  }

  void compareStraight()
      throws InvalidCardInputException, EvaluateHandException {

    String hand1 = "C5 D3 D4 S7 C6";
    String hand2 = "DA D3 D5 H8 S8";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.STRAIGHT));

    hand1 = "CA D4 D2 S3 C5";
    hand2 = "DA D3 D5 H2 S4";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(tie(EHandOutcome.STRAIGHT));

    hand1 = "CA D4 D2 S3 C5";
    hand2 = "D2 D4 D3 H5 S6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(secondWins(EHandOutcome.STRAIGHT));

    hand1 = "CA D4 D2 S3 C5";
    hand2 = "DA DJ DT HK SQ";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(secondWins(EHandOutcome.STRAIGHT));
  }

  void compareFourOfAKind()
      throws InvalidCardInputException, EvaluateHandException {

    String hand1 = "C5 D5 H5 S5 C6";
    String hand2 = "DA D3 D5 H8 S8";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.FOUR_OF_A_KIND));

    hand1 = "C5 D5 H5 S5 C6";
    hand2 = "C7 D7 H7 S7 C6";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(secondWins(EHandOutcome.FOUR_OF_A_KIND));
  }

  void compareFullHouse()
      throws InvalidCardInputException, EvaluateHandException {

    String hand1 = "C5 D5 H5 S6 C6";
    String hand2 = "DA D3 D5 H8 S8";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(firstWins(EHandOutcome.FULL_HOUSE));

    hand1 = "C5 D5 H5 S6 C6";
    hand2 = "C7 D7 H7 SA CA";
    Assertions.assertThat(Poker.compareHands(hand1, hand2))
        .isEqualTo(secondWins(EHandOutcome.FULL_HOUSE));
  }

  private String firstWins(final EHandOutcome handOutcome) {
    return String.format("1st hand wins! (%s)", handOutcome);
  }

  private String secondWins(final EHandOutcome handOutcome) {
    return String.format("2nd hand wins! (%s)", handOutcome);
  }

  private String tie(final EHandOutcome handOutcome) {
    return String.format("It is a (%s) tie!", handOutcome);
  }

}
