package com.flaregames.poker.exceptions;

/**
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 12/12/2017
 */
public final class EvaluateHandException extends PokerException {

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message
   */
  public EvaluateHandException(final String message) {
    super(message);
  }
}
