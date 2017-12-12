package com.flaregames.poker;

import com.flaregames.poker.datatypes.Card;
import com.flaregames.poker.datatypes.Hand;
import com.flaregames.poker.enums.ECardSuit;
import com.flaregames.poker.enums.ECardValue;
import com.flaregames.poker.enums.EHandOutcome;
import com.flaregames.poker.exceptions.InvalidCardInputException;
import com.flaregames.poker.exceptions.InvalidHandSizeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Poker {

  /**
   * Class logger.
   */
  private static final Logger logger = LoggerFactory.getLogger(Poker.class);

  /**
   * Regular expression to match a valid card (suit & value).
   */
  private static final String inputRegex = "[CDHS][2-9TJQKA]";

  /**
   * Application entry point.
   *
   * @param args command line arguments
   * @throws FileNotFoundException if the input file is not found
   */
  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(final String[] args)
      throws FileNotFoundException {

    // TODO remove and use user input?
    System.setIn(new FileInputStream("sample_input.txt"));

    final Scanner scanner = new Scanner(System.in, "UTF-8");
    final String rawHandInput1 = scanner.nextLine();
    final String rawHandInput2 = scanner.nextLine();

    final Poker poker = new Poker();
    try {
      System.out.println(poker.compareHands(rawHandInput1, rawHandInput2));
    } catch (final InvalidHandSizeException | InvalidCardInputException ex) {
      logger.error("Could not handle input.", ex);
    }
  }

  String compareHands(final String rawHandInput1, final String rawHandInput2)
      throws InvalidHandSizeException, InvalidCardInputException {

    final EHandOutcome outcome1 = evaluateHand(validateAndProcessInput(rawHandInput1));
    final EHandOutcome outcome2 = evaluateHand(validateAndProcessInput(rawHandInput2));

    if (outcome1.compareTo(outcome2) > 0) {
      return String.format("1st hand wins! (%s)", outcome1);
    }
    if (outcome1.compareTo(outcome2) < 0) {
      return String.format("2nd hand wins! (%s)", outcome2);
    }
    return String.format("It is a (%s) tie!", outcome1);
  }

  private Hand validateAndProcessInput(final String rawHandInput)
      throws InvalidHandSizeException, InvalidCardInputException {

    final Card[] cards = new Card[Hand.HAND_SIZE];

    final Pattern pattern = Pattern.compile(inputRegex);
    final Matcher matcher = pattern.matcher(rawHandInput);
    for (int i = 0; i < Hand.HAND_SIZE; i++) {
      if (!matcher.find()) {
        throw new InvalidCardInputException(String.format(
            "[%s] does not match regular expression [%s].", rawHandInput, inputRegex));
      }
      cards[i] = Card.from(matcher.group());
    }

    return Hand.of(Arrays.asList(cards));
  }

  EHandOutcome evaluateHand(final Hand hand) {

    final Map<Card, Integer> map = new TreeMap<>();

    for (final Card card : hand.getCards()) {
      map.compute(card, (card1, count) -> count != null ? count + 1 : 1);
    }

    if (map.size() == 2) {
      final Integer firstValue = map.values().iterator().next();
      if (firstValue == 1 || firstValue == 4) {
        return EHandOutcome.FOUR_OF_A_KIND;
      } else {
        return EHandOutcome.FULL_HOUSE;
      }
    }

    if (map.size() == 3) {
      for (final Integer count : map.values()) {
        if (count == 3) {
          return EHandOutcome.THREE_OF_A_KIND;
        }
      }
      return EHandOutcome.TWO_PAIRS;
    }

    if (map.size() == 4) {
      return EHandOutcome.PAIR;
    }

    if (map.size() == 5) {
      if (allSameSuit(map.keySet())) {
        if (consecutiveValues(map.keySet())) {
          return EHandOutcome.STRAIGHT_FLUSH;
        } else {
          return EHandOutcome.FLUSH;
        }
      } else {
        if (consecutiveValues(map.keySet())) {
          return EHandOutcome.STRAIGHT;
        }
      }
    }

    return EHandOutcome.HIGH_CARD;
  }

  private boolean consecutiveValues(final Set<Card> cards) {

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

  private boolean allSameSuit(final Set<Card> cards) {
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
