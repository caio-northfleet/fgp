package com.flaregames.poker.exceptions;

/**
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 10/12/2017
 */
public abstract class PokerException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message
   */
  PokerException(final String message) {
    super(message);
  }

  /**
   * Constructs a new exception with the specified cause.
   *
   * @param cause the cause
   */
  PokerException(final Throwable cause) {
    super(cause);
  }
}
