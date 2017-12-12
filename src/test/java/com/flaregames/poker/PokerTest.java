package com.flaregames.poker;

import com.flaregames.poker.datatypes.Card;
import com.flaregames.poker.datatypes.Hand;
import com.flaregames.poker.enums.EHandOutcome;
import com.flaregames.poker.exceptions.InvalidCardInputException;
import com.flaregames.poker.exceptions.InvalidHandSizeException;

import org.apache.commons.math3.util.Combinations;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PokerTest {

  private static final int DECK_SIZE = 52;
  private static final int CARDS_PER_SUIT = 13;

  @Test
  @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
  public void testAllCombinations()
      throws InvalidCardInputException, InvalidHandSizeException {

    // all valid card suits and values
    final String suits = "CDHS";
    final String values = "23456789TJQKA";

    // initialize counters map
    final Map<EHandOutcome, Integer> counters = new HashMap<>();
    for (final EHandOutcome outcome : EHandOutcome.values()) {
      counters.put(outcome, 0);
    }

    final Poker poker = new Poker();

    // loop through all combinations of the deck cards per hand
    for (final int[] combination : new Combinations(DECK_SIZE, Hand.HAND_SIZE)) {

      // build up an array of cards bases on a combination
      final Card[] cards = new Card[Hand.HAND_SIZE];
      for (int i = 0; i < Hand.HAND_SIZE; i++) {
        final char suit = suits.charAt(combination[i] / CARDS_PER_SUIT);
        final char value = values.charAt(combination[i] % CARDS_PER_SUIT);
        cards[i] = Card.from(String.valueOf(suit) + value);
      }

      // evaluate hand of cards and increment appropriate results counter
      final EHandOutcome outcome = poker.evaluateHand(Hand.of(Arrays.asList(cards)));
      counters.replace(outcome, counters.get(outcome) + 1);
    }

    // assert counters (numbers got from http://suffe.cool/poker/evaluator.html)
    int total = 0;
    for (final Integer count : counters.values()) {
      total += count;
    }
    Assertions.assertThat(total).isEqualTo(2598960);
    Assertions.assertThat(counters.get(EHandOutcome.STRAIGHT_FLUSH)).isEqualTo(40);
    Assertions.assertThat(counters.get(EHandOutcome.FOUR_OF_A_KIND)).isEqualTo(624);
    Assertions.assertThat(counters.get(EHandOutcome.FULL_HOUSE)).isEqualTo(3744);
    Assertions.assertThat(counters.get(EHandOutcome.FLUSH)).isEqualTo(5108);
    Assertions.assertThat(counters.get(EHandOutcome.STRAIGHT)).isEqualTo(10200);
    Assertions.assertThat(counters.get(EHandOutcome.THREE_OF_A_KIND)).isEqualTo(54912);
    Assertions.assertThat(counters.get(EHandOutcome.TWO_PAIRS)).isEqualTo(123552);
    Assertions.assertThat(counters.get(EHandOutcome.PAIR)).isEqualTo(1098240);
    Assertions.assertThat(counters.get(EHandOutcome.HIGH_CARD)).isEqualTo(1302540);
  }

  @Test
  public void testCompareHands()
      throws InvalidHandSizeException, InvalidCardInputException {

    final String hand1 = "C5 D3 D4 S7 C6";
    final String hand2 = "DA D3 D5 H8 S8";

    final Poker poker = new Poker();

    Assertions.assertThat(poker.compareHands(hand1, hand2))
        .isEqualTo("1st hand wins! (Straight)");
  }
}
