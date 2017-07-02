package se.itu.hunt;

/**
 * <p>Represents a {@link Position} on a {@link Board} in this game.
 * Uses a classic (x, y) representation for a coordinate.</p>
 * <p>This class is immutable.</p>
 */
public class Position {

  /** The x coordinate */
  protected final int x;
  /** The y coordinate */
  protected final int y;

  /**
   * Constructs a new {@link Position} at (x, y).
   * @param x The x coordinate for this {@link Position}
   * @param y The y coordinate for this {@link Position}
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns this {@link Position}'s x coordinate.
   * @return This {@link Position}'s x coordinate
   */
  public int x() { return x; }

  /**
   * Returns this {@link Position}'s y coordinate.
   * @return This {@link Position}'s y coordinate
   */
  public int y() { return y; }

  /**
   * Returns a new {@link Position} "north" of this {@link Position}.
   * @return A new {@link Position} "north" of this {@link Position}
   */
  public Position north() {
    return new Position(x, y+1);
  }
  
  /**
   * Returns a new {@link Position} "south" of this {@link Position}.
   * @return A new {@link Position} "south" of this {@link Position}
   */
  public Position south() {
    return new Position(x, y-1);
  }
  
  /**
   * Returns a new {@link Position} "east" of this {@link Position}.
   * @return A new {@link Position} "east" of this {@link Position}
   */
  public Position east() {
    return new Position(x+1, y);
  }
  
  /**
   * Returns a new {@link Position} "west" of this {@link Position}.
   * @return A new {Position} "west" of this {@link Position}
   */
  public Position west() {
    return new Position(x-1, y);
  }

  /**
   * Checks if this {@link Position} equals the given {@link Position}.
   * Two {@link Position}s are considered equal if they have the same coordinates.
   * @return true if this {@link Position} equals the given {@link Position}, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (o == this) {
      return true;
    }
    if (! (o instanceof Position)) {
      return false;
    }
    Position that = (Position)o;
    return that.x == x && that.y == y;
  }

  /**
   * Returns the hashCode for this {@link Position}.
   * The hash code is calculated using the x and y coordinates.
   * @return The hashCode for this {@link Position}
   */
  @Override
  public int hashCode() {
    int code = 17;
    code += x * 31;
    code += y * 31;
    return code;
  }

  /**
   * Returns a {@link String} representation of this {@link Position}.
   * @return This {@link Position} as a {@link String}
   */
  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

}
