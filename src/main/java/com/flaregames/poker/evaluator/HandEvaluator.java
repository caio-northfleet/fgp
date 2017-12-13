package com.flaregames.poker.evaluator;

import com.google.common.collect.Lists;

import com.flaregames.poker.datatypes.Card;
import com.flaregames.poker.datatypes.Hand;
import com.flaregames.poker.enums.ECardSuit;
import com.flaregames.poker.enums.ECardValue;
import com.flaregames.poker.enums.EHandOutcome;
import com.flaregames.poker.exceptions.EvaluateHandException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * The Poker hand evaluator.
 *
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @see HandEvaluatorResult
 * @see EHandOutcome
 * @since 12/12/2017
 */
public final class HandEvaluator {

  /**
   * Evaluate the provided Poker hand.
   *
   * @param hand the Poker hand to be evaluated
   * @return a {@link HandEvaluatorResult} with the results of the Poker hand evaluation
   * @throws EvaluateHandException in case an issue happens during evaluation
   */
  public static HandEvaluatorResult evaluateHand(final Hand hand)
      throws EvaluateHandException {

    final Map<Card, Integer> map = new TreeMap<>();

    for (final Card card : hand.getCards()) {
      map.compute(card, (card1, count) -> count != null ? count + 1 : 1);
    }

    // "Four of a Kind" or "Full House"
    if (map.size() == 2) {
      return evaluateHand2(map);
    }
    // "Three of a Kind" or "Two Pairs"
    if (map.size() == 3) {
      return evaluateHand3(map);
    }
    // "One Pair"
    if (map.size() == 4) {
      return evaluateHand4(map);
    }
    // "Straight", "Flush", "Straight Flush" or "High Card"
    if (map.size() == 5) {
      return evaluateHand5(map);
    }

    throw new EvaluateHandException("Weird behaviour analysing hand.");
  }

  /**
   * Evaluate the provide Poker hand which has 2 separate card groups in it. Result will be either
   * {@link EHandOutcome#FOUR_OF_A_KIND} or {@link EHandOutcome#FULL_HOUSE}.
   *
   * @param hand the Poker hand to be evaluated
   * @return a {@link HandEvaluatorResult} with the results of the Poker hand evaluation
   */
  private static HandEvaluatorResult evaluateHand2(final Map<Card, Integer> hand) {

    final Map.Entry<Card, Integer> firstEntry = hand.entrySet().iterator().next();
    final Integer firstValue = firstEntry.getValue();

    ECardValue cardValue = firstEntry.getKey().getValue();
    if (firstValue == 1) {
      cardValue = hand.entrySet().iterator().next().getKey().getValue();
    }
    if (firstValue == 1 || firstValue == 4) {
      return HandEvaluatorResult.from(EHandOutcome.FOUR_OF_A_KIND, cardValue);
    }
    if (firstValue == 2) {
      cardValue = hand.entrySet().iterator().next().getKey().getValue();
    }
    return HandEvaluatorResult.from(EHandOutcome.FULL_HOUSE, cardValue);
  }

  /**
   * Evaluate the provide Poker hand which has 3 separate card groups in it. Result will be either
   * {@link EHandOutcome#THREE_OF_A_KIND} or {@link EHandOutcome#TWO_PAIRS}.
   *
   * @param hand the Poker hand to be evaluated
   * @return a {@link HandEvaluatorResult} with the results of the Poker hand evaluation
   */
  private static HandEvaluatorResult evaluateHand3(final Map<Card, Integer> hand)
      throws EvaluateHandException {

    for (final Map.Entry<Card, Integer> entries : hand.entrySet()) {
      if (entries.getValue() == 3) {
        return HandEvaluatorResult.from(EHandOutcome.THREE_OF_A_KIND, entries.getKey().getValue());
      }
    }

    boolean firstPairFound = false;
    ECardValue firstPairCardValue = null;
    ECardValue secondPairCardValue = null;
    ECardValue leftCardValue = null;
    for (final Map.Entry<Card, Integer> entries : hand.entrySet()) {
      if (entries.getValue() == 2) {
        if (!firstPairFound) {
          firstPairCardValue = entries.getKey().getValue();
          firstPairFound = true;
        } else {
          secondPairCardValue = entries.getKey().getValue();
        }
      } else {
        leftCardValue = entries.getKey().getValue();
      }
    }
    if (firstPairCardValue == null || secondPairCardValue == null || leftCardValue == null) {
      throw new EvaluateHandException("Weird behaviour analysing hand.");
    }
    if (firstPairCardValue.getCardValue() > secondPairCardValue.getCardValue()) {
      return HandEvaluatorResult.from(EHandOutcome.TWO_PAIRS, firstPairCardValue,
          secondPairCardValue, leftCardValue);
    } else {
      return HandEvaluatorResult.from(EHandOutcome.TWO_PAIRS, secondPairCardValue,
          firstPairCardValue, leftCardValue);
    }
  }

  /**
   * Evaluate the provide Poker hand which has 4 separate card groups in it. Result will be {@link
   * EHandOutcome#PAIR}.
   *
   * @param hand the Poker hand to be evaluated
   * @return a {@link HandEvaluatorResult} with the results of the Poker hand evaluation
   */
  private static HandEvaluatorResult evaluateHand4(final Map<Card, Integer> hand)
      throws EvaluateHandException {

    List<ECardValue> cardValues = new ArrayList<>();
    ECardValue pairCardValue = null;
    for (final Map.Entry<Card, Integer> entries : hand.entrySet()) {
      if (entries.getValue() == 2) {
        pairCardValue = entries.getKey().getValue();
      } else {
        cardValues.add(entries.getKey().getValue());
      }
    }
    if (pairCardValue == null) {
      throw new EvaluateHandException("Weird behaviour analysing hand.");
    }
    cardValues.add(pairCardValue);

    return HandEvaluatorResult.from(EHandOutcome.PAIR,
        Lists.reverse(cardValues).toArray(new ECardValue[0]));
  }

  /**
   * Evaluate the provide Poker hand which has 5 separate card groups in it. Result will be {@link
   * EHandOutcome#STRAIGHT}, {@link EHandOutcome#FLUSH}, {@link EHandOutcome#STRAIGHT_FLUSH} or a
   * simple {@link EHandOutcome#HIGH_CARD}.
   *
   * @param hand the Poker hand to be evaluated
   * @return a {@link HandEvaluatorResult} with the results of the Poker hand evaluation
   */
  private static HandEvaluatorResult evaluateHand5(final Map<Card, Integer> hand) {

    final List<ECardValue> cardValues = new ArrayList<>();
    hand.keySet().forEach(card -> cardValues.add(card.getValue()));

    if (allSameSuit(hand.keySet())) {
      if (consecutiveValues(hand.keySet())) {
        final boolean aceAsOne = cardValues.contains(ECardValue.TWO)
            && cardValues.contains(ECardValue.ACE);
        return HandEvaluatorResult.from(EHandOutcome.STRAIGHT_FLUSH,
            Lists.reverse(cardValues).get(aceAsOne ? cardValues.size() - 1 : 0));
      } else {
        return HandEvaluatorResult.from(EHandOutcome.FLUSH,
            Lists.reverse(cardValues).toArray(new ECardValue[0]));
      }
    } else {
      if (consecutiveValues(hand.keySet())) {
        final boolean aceAsOne = cardValues.contains(ECardValue.TWO)
            && cardValues.contains(ECardValue.ACE);
        return HandEvaluatorResult.from(EHandOutcome.STRAIGHT,
            Lists.reverse(cardValues).get(aceAsOne ? cardValues.size() - 1 : 0));
      }
    }

    return HandEvaluatorResult.from(EHandOutcome.HIGH_CARD,
        Lists.reverse(cardValues).toArray(new ECardValue[0]));
  }

  /**
   * Check a set of cards to identify if their values are all consecutive numbers. A special
   * attention is given to the Ace as it can be either the lower card or the higher card value.
   *
   * @param cards the set of cards to be checked
   * @return whether or not the supplied cards have consecutive values
   */
  private static boolean consecutiveValues(final Set<Card> cards) {

    final Iterator<Card> cardIterator = cards.iterator();
    final ECardValue firstCardValue = cardIterator.next().getValue();
    ECardValue previousCardValue = firstCardValue;
    boolean consecutiveValues = true;
    int count = 1;
    while (cardIterator.hasNext()) {
      final ECardValue cardValue = cardIterator.next().getValue();
      if (cardValue.getCardValue() != previousCardValue.getCardValue() + 1) {
        final boolean aceAsOne = count == cards.size() - 1
            && cardValue.equals(ECardValue.ACE) && firstCardValue.equals(ECardValue.TWO);
        if (!aceAsOne) {
          consecutiveValues = false;
          break;
        }
      }
      previousCardValue = cardValue;
      count++;
    }
    return consecutiveValues;
  }

  /**
   * Check a set of cards to identify if all cards have the same suit.
   *
   * @param cards the set of cards to be checked
   * @return whether or not the supplied cards all have the same suit
   */
  private static boolean allSameSuit(final Set<Card> cards) {
    final Iterator<Card> cardIterator = cards.iterator();
    final ECardSuit firstCardSuit = cardIterator.next().getSuit();
    boolean allSameSuit = true;
    while (cardIterator.hasNext()) {
      final ECardSuit cardSuit = cardIterator.next().getSuit();
      if (!cardSuit.equals(firstCardSuit)) {
        allSameSuit = false;
        break;
      }
    }
    return allSameSuit;
  }
}
