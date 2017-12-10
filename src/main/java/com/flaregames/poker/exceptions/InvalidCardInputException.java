package com.flaregames.poker.exceptions;

/**
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 10/12/2017
 */
public final class InvalidCardInputException extends PokerException {

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message
   */
  public InvalidCardInputException(final String message) {
    super(message);
  }
}
