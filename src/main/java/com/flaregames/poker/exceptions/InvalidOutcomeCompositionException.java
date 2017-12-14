package com.flaregames.poker.exceptions;

/**
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 13/12/2017
 */
public final class InvalidOutcomeCompositionException extends PokerException {

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message
   */
  public InvalidOutcomeCompositionException(final String message) {
    super(message);
  }
}
