package com.flaregames.poker.exceptions;

/**
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 10/12/2017
 */
public final class InvalidHandSizeException extends PokerException {

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message
   */
  public InvalidHandSizeException(final String message) {
    super(message);
  }
}
