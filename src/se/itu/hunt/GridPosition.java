package se.itu.hunt;

/**
 * <p>Represents an immutable {@link Position} to be used in a matrix (two dimensional array) together with a GridLayout.</p>
 * <p>Note that a GridLayout has (0,0) in the upper left corner, representing row 0, column zero.</p>
 */
public class GridPosition extends Position {

  private int row;
  private int col;
  
  /**
   * Constructs a new {@link GridPosition} at coordinates (x,y).
   * @param row The y coordinate for this {@link GridPosition}
   * @param col The x coordinate for this {@link GridPosition}
   */
  public GridPosition(int row, int col) {
    super(col, row);
    this.row = row;
    this.col = col;
  }

  /**
   * <p>Returns a new {@link GridPosition} with the coordinates
   * to the north of this {@link GridPosition}.</p>
   * <p>From row 5, column 5, north is row 4, colum 5</p>
   * @return A new GridPosition north of this GridPosition
   */
  @Override
  public Position north() {
    return new GridPosition(row-1, col);
  }
  
  /**
   * <p>Returns a new {@link GridPosition} with the coordinates
   * to the south of this {@link GridPosition}.</p>
   * <p>From row 5, column 5, south is row 6, colum 5</p>
   * @return A new {@link GridPosition} south of this {@link GridPosition}
   */
  @Override
  public Position south() {
    return new GridPosition(row+1, col);
  }
  
  /**
   * <p>Returns a new {@link GridPosition} with the coordinates
   * to the east of this {@link GridPosition}.</p>
   * <p>From row 5, column 5, east is row 5, colum 6</p>
   * @return A new {@link GridPosition} east of this {@link GridPosition}
   */  
  @Override
  public Position east() {
    return new GridPosition(row, col+1);
  }

  /**
   * <p>Returns a new {@link GridPosition} with the coordinates
   * to the west of this {@link GridPosition}.</p>
   * <p>From row 5, column 5, west is row 5, colum 4</p>
   * @return A new {@link GridPosition} west of this {@link GridPosition}
   */  
  @Override
  public Position west() {
    return new GridPosition(row, col-1);
  }

  /**
   * Returns a {@link String} representaion of this {@link GridPosition} on the format:
   * <code>(row: 3, column: 5)</code> where:<br>
   * 3 represents the y coordinate and 5 represents the x coordinate
   * @return This {@link GridPosition} as a {@link String} 
   */
  @Override
  public String toString() {
    return "(row: " + row + " col: " + col + ")";
  }
}
