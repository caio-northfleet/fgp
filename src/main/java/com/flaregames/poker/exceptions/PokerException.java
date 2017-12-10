package com.flaregames.poker.exceptions;

/**
 * @author Caio Northfleet (caio.northfleet@gmail.com)
 * @since 10/12/2017
 */
abstract class PokerException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message
   */
  PokerException(final String message) {
    super(message);
  }
}
