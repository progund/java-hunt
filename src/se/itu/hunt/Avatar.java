package se.itu.hunt;

/**
 * <p>Represents the player character in the game.</p>
 * <p>To set up a game, you typically do something like this:
 * <pre>
 * Dimension dimension = new Dimension(10,10); // size of the board
 * Board board = new Board(dimension);
 * Avatar player = new Avatar(board, pos);
 * GameUI game = new GUIGame(avatar, board);
 * game.displayAndRun(); // Show the game and start it!
 * </pre>
 */
public class Avatar {
  private Position pos;
  private Board board;

  /**
   * Constructs a new {@link Avatar} with a {@link Board} and
   * a {@link Position}.
   * @param board The {@link Board} this {@link Avatar} lives on
   * @param pos The {@link Position} where this {@link Avatar} starts on the Board
   */
  public Avatar(Board board, Position pos) {
    this.board = board;
    this.pos = pos;
  }

  /**
   * Returns the current {@link se.itu.hunt.Position} of this {@link Avatar}.
   * @return The current {@link se.itu.hunt.Position} of this {@link Avatar}
   */
  public Position currentPosition() {
    return pos;
  }

  /**
   * Returns a boolean value representing whether this {@link Avatar}
   * has found the treasure on the {@link Board}.
   * @return true if the treasure is found (e.g. this {@link Avatar} is
   * standing at the {@link Position} on the {@link Board} where the treasure is),
   * false otherwise.
   */
  public boolean foundIt() {
    return board.isTreasureAt(pos);
  }

  /**
   * Moves this {@link Avatar} in the given {@link Direction}.
   * @param direction The {@link Direction} to move this {@link Avatar} in
   * @throws IllegalMoveException if it is not possible to go 
   * in the given {@link Direction}
   */
  public void move(Direction direction) throws IllegalMoveException {
    Position newPos = null;
    switch(direction) {
      case NORTH:
        newPos = pos.north();
        break;
      case SOUTH:
        newPos = pos.south();
        break;
      case EAST:
        newPos = pos.east();
        break;
      case WEST:
        newPos = pos.west();
    }
    if (board.containsPosition(newPos)) {
      this.pos = newPos;
    } else {
      throw new IllegalMoveException(direction + " is outside the board.");
    }
  }

  /**
   * Returns a {@link java.lang.String} representation of this {@link Avatar}
   * @return This {@link Avatar}, as a {@link java.lang.String} on the format
   * <code>"Avatar at position (x, y)"</code>
   */
  @Override
  public String toString() {
    return "Avatar at position " + pos;
  }
}
