package se.itu.hunt;

/**
 * <p>
 * Represents a board for the game, with a treasure at a random {@link Position}.
 * </p>
 * <p>
 * A board has a {@link Dimension}, e.g.  (10 X 10) and
 * typically has Position (0, 0) as its lower left corner. Other geometries will
 * use a subclass of {@link Position} to handle coordinates differently.
 * </p>
 */
public class Board {

  private Dimension dimension;
  private Position treasurePos;

  /**
   * <p>Constructs a new {@link Board} of a given {@link Dimension}.
   * The {@link Board} will generate a random {@link Position} for the
   * treasure.</p>
   * @param dimension This {@link Board}'s Dimension
   */
  public Board(Dimension dimension) {
    this.dimension = dimension;
    this.treasurePos = getTreasurePosition();
  }

  /**
   * Returns this Board's width
   * @return This Board's width
   */
  public int width() { return dimension.width(); }

  /**
   * Returns this Board's height
   * @return This Board's height
   */
  public int height() { return dimension.height(); }

  /**
   * Returns this Board's Dimension
   * @return This Board's Dimension
   */
  public Dimension dimension() { return dimension; }
  
  /**
   * Returns true if this Board contains the Position given as argument.
   * A Board contains a Position if 0 &lt;= Position.x and Position.x &lt; width
   * and 0 &lt;= Position.y and Position.y &lt; height.
   * 
   * @param pos The Position to check if this Board contains
   * @return true if this Board contains the Position, false otherwise.
   */
  public boolean containsPosition(Position pos) {
    return pos.x() >= 0 &&
      pos.x() < width() &&
      pos.y() >= 0 &&
      pos.y() < height();
  }

  /**
   * Returns this {@link Board}'s treasure {@link Position}. Makes
   * debugging (and cheating) easier.
   * @return the {@link Position} of this {@link Board}'s treasure
   */
  public Position treasurePosition() {
    return treasurePos;
  }
  
  private Position getTreasurePosition() {
    java.util.Random random = new java.util.Random(System.currentTimeMillis());
    return new Position(random.nextInt(width()), random.nextInt(height()));
  }
  
  /**
   * Returns true if the treasure is at the given Position
   * @param pos The position to check
   * @return true if the treasure is at the given Position, false otherwise
   */
  public boolean isTreasureAt(Position pos) {
    return treasurePos.equals(pos);
  }

  /**
   * Returns a java.lang.String representation of this Board on the form:
   * <code>Board of dimension (10 X 10) with treasure at (5, 5)</code>
   * @return A java.lang.String representation of this Board
   */
  @Override
  public String toString() {
    return new StringBuilder("Board of dimension ")
      .append(dimension)
      .append(" with treasure at ")
      .append(treasurePos)
      .toString();
  }
}
