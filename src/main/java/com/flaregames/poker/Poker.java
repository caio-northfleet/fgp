package com.flaregames.poker;

import com.flaregames.poker.datatypes.Card;
import com.flaregames.poker.datatypes.Hand;
import com.flaregames.poker.evaluator.HandEvaluator;
import com.flaregames.poker.evaluator.HandEvaluatorResult;
import com.flaregames.poker.exceptions.EvaluateHandException;
import com.flaregames.poker.exceptions.InvalidCardInputException;
import com.flaregames.poker.exceptions.InvalidHandSizeException;

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

    // check if a file path was passed as a command line argument
    if (args.length == 1) {
      // change standard input to the specified file path
      System.setIn(new FileInputStream(args[0]));
    }

    // read two hands from the input stream
    final Scanner scanner = new Scanner(System.in, "UTF-8");
    final String rawHandInput1 = scanner.nextLine();
    final String rawHandInput2 = scanner.nextLine();

    try {
      // compare hands and print out the result
      System.out.println(compareHands(rawHandInput1, rawHandInput2));
    } catch (final InvalidCardInputException | EvaluateHandException ex) {
      logger.error("Could not compare input hands.", ex);
    }
  }

  /**
   * Compare two Poker hands.
   *
   * @param rawHandInput1 the first Poker hand
   * @param rawHandInput2 the second Poker hand
   * @return the comparison result
   * @throws InvalidCardInputException if the input strings do not match the correct format/pattern
   * @throws EvaluateHandException     if an error happens while evaluating the Poker hands
   */
  static String compareHands(final String rawHandInput1, final String rawHandInput2)
      throws InvalidCardInputException, EvaluateHandException {

    final HandEvaluatorResult result1 =
        HandEvaluator.evaluateHand(validateAndProcessInput(rawHandInput1));
    final HandEvaluatorResult result2 =
        HandEvaluator.evaluateHand(validateAndProcessInput(rawHandInput2));

    System.out.println("1st hand: " + rawHandInput1);
    System.out.println("2nd hand: " + rawHandInput2);
    
    if (result1.compareTo(result2) > 0) {
      return String.format("1st hand wins! (%s)", result1);
    }
    if (result1.compareTo(result2) < 0) {
      return String.format("2nd hand wins! (%s)", result2);
    }
    return String.format("It is a (%s) tie!", result1);
  }

  /**
   * Process the raw Poker hand input into a {@link Hand} instance.
   *
   * @param rawHandInput the raw Poker hand input
   * @return the {@link Hand} instance corresponding to the raw input
   * @throws InvalidCardInputException if the input strings do not match the correct format/pattern
   */
  private static Hand validateAndProcessInput(final String rawHandInput)
      throws InvalidCardInputException {

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

    try {
      return Hand.of(Arrays.asList(cards));
    } catch (final InvalidHandSizeException ex) {
      throw new InvalidCardInputException(ex);
    }
  }
}
