package com.flaregames.poker;

import com.flaregames.poker.datatypes.Card;
import com.flaregames.poker.datatypes.Hand;
import com.flaregames.poker.exceptions.InvalidCardInputException;
import com.flaregames.poker.exceptions.InvalidHandSizeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Poker {

  private static final Logger logger = LoggerFactory.getLogger(Poker.class);

  /**
   * Application entry point.
   *
   * @param args command line arguments
   */
  public static void main(final String[] args) {

    final String c1 = "X5";
    final String c2 = "D3";
    final String c3 = "D4";
    final String c4 = "S7";
    final String c5 = "C6";

    final Poker poker = new Poker();
    try {
      poker.evaluateHand(
          Hand.of(
              Card.from(c1),
              Card.from(c2),
              Card.from(c3),
              Card.from(c4),
              Card.from(c5)));
    } catch (final InvalidHandSizeException | InvalidCardInputException ex) {
      logger.error("Could not evaluate hand.", ex);
    }
  }

  private void evaluateHand(final Hand hand) {
    logger.info(hand.toString());
  }
}
