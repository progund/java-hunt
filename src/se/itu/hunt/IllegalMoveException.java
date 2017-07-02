package se.itu.hunt;

/**
 * Represents the Exception for when an Avatar tries to move
 * in a direction outside of the Board.
 */
public class IllegalMoveException extends Exception {
  /**
   * Constructs a new IllegalMoveException with the
   * given message String.
   * @param msg The message for this Exception
   */
  public IllegalMoveException(String msg) {
    super(msg);
  }
}
