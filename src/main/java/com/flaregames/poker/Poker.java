package com.flaregames.poker;

import com.flaregames.poker.datatypes.Card;
import com.flaregames.poker.datatypes.Hand;
import com.flaregames.poker.enums.EHandOutcome;
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

@SuppressWarnings("PMD.SystemPrintln")
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
  public static void main(final String[] args)
      throws FileNotFoundException {

    // TODO remove and use user input?
    System.setIn(new FileInputStream("sample_input.txt"));

    final Scanner scanner = new Scanner(System.in, "UTF-8");
    final String rawHandInput1 = scanner.nextLine();
    final String rawHandInput2 = scanner.nextLine();

    final Poker poker = new Poker();

    final Hand hand1;
    final Hand hand2;
    try {
      hand1 = poker.validateAndProcessInput(rawHandInput1);
      hand2 = poker.validateAndProcessInput(rawHandInput2);
    } catch (final InvalidHandSizeException | InvalidCardInputException ex) {
      logger.error("Could not handle input.", ex);
      return;
    }

    final int result1 = poker.evaluateHand(hand1);
    final int result2 = poker.evaluateHand(hand2);

    if (result1 > result2) {
      System.out.println(String.format("1st hand wins! (%s)", poker.translateResult(result1)));
    } else if (result2 > result1) {
      System.out.println(String.format("2nd hand wins! (%s)", poker.translateResult(result2)));
    } else {
      System.out.println(String.format("It is a (%s) tie!", poker.translateResult(result1)));
    }
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

  private int evaluateHand(final Hand hand) {
    logger.info(String.format("Evaluating hand [%s]...", hand));

    // TODO real evaluation

    return 0;
  }

  private String translateResult(final int result) {
    logger.info(String.format("Translating result [%d]...", result));

    // TODO real translation

    return EHandOutcome.FOUR_OF_A_KIND.toString();
  }
}
