package com.flaregames.poker;

import com.flaregames.poker.datatypes.Card;
import com.flaregames.poker.datatypes.Hand;
import com.flaregames.poker.evaluator.HandEvaluator;
import com.flaregames.poker.evaluator.HandEvaluatorResult;
import com.flaregames.poker.exceptions.EvaluateHandException;
import com.flaregames.poker.exceptions.InvalidCardInputException;
import com.flaregames.poker.exceptions.InvalidHandSizeException;
import com.flaregames.poker.exceptions.PokerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
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
   * Hidden class constructor.
   */
  private Poker() {
  }

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

    try {
      System.out.println(compareHands(rawHandInput1, rawHandInput2));
    } catch (final PokerException ex) {
      logger.error("Could not compare input hands.", ex);
    }
  }

  static String compareHands(final String rawHandInput1, final String rawHandInput2)
      throws InvalidHandSizeException, InvalidCardInputException, EvaluateHandException {

    final HandEvaluatorResult result1 =
        HandEvaluator.evaluateHand(validateAndProcessInput(rawHandInput1));
    final HandEvaluatorResult result2 =
        HandEvaluator.evaluateHand(validateAndProcessInput(rawHandInput2));

    if (result1.compareTo(result2) > 0) {
      return String.format("1st hand wins! (%s)", result1);
    }
    if (result1.compareTo(result2) < 0) {
      return String.format("2nd hand wins! (%s)", result2);
    }
    return String.format("It is a (%s) tie!", result1);
  }

  private static Hand validateAndProcessInput(final String rawHandInput)
      throws InvalidHandSizeException, InvalidCardInputException {

    final Card[] cards = new Card[Hand.HAND_SIZE];

    final Pattern pattern = Pattern.compile(inputRegex);
    final Matcher matcher = pattern.matcher(rawHandInput);
    for (int i = 0; i < Hand.HAND_SIZE; i++) {
      if (!matcher.find()) {
        throw new InvalidCardInputException(String.format(
            "[%s] does not match the regular expression [%s].", rawHandInput, inputRegex));
      }
      cards[i] = Card.from(matcher.group());
    }

    return Hand.of(Arrays.asList(cards));
  }
}
