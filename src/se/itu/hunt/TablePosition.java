package se.itu.hunt;

/**
 * <p>Represents an immutable {@link Position} to be used in a {@link javax.swing.JTable}.</p>
 * <p>Note that a {@link javax.swing.JTable} has (0,0) in the upper left corner.</p>
 * <p>Since Swing uses a method <code>getValueAt(int row, int col)</code>,
 * we'll swap the x and y values in the call to super().</p>
 */
public class TablePosition extends Position {

  /**
   * Constructs a new {@link TablePosition} at coordinates (x,y).
   * @param row The y coordinate for this {@link TablePosition}
   * @param col The x coordinate for this {@link TablePosition}
   */
  public TablePosition(int row, int col) {
    super(col, row);
  }

  /**
   * <p>Returns a new {@link TablePosition} with the coordinates
   * to the north of this {@link TablePosition}.</p>
   * <p>From row 5, column 5, north is row 4, colum 5</p>
   * @return A new TablePosition north of this TablePosition
   */
  @Override
  public Position north() {
    return new TablePosition(y-1, x);
  }
  
  /**
   * <p>Returns a new {@link TablePosition} with the coordinates
   * to the south of this {@link TablePosition}.</p>
   * <p>From row 5, column 5, south is row 6, colum 5</p>
   * @return A new {@link TablePosition} south of this {@link TablePosition}
   */
  @Override
  public Position south() {
    return new TablePosition(y+1, x);
  }
  
  /**
   * <p>Returns a new {@link TablePosition} with the coordinates
   * to the east of this {@link TablePosition}.</p>
   * <p>From row 5, column 5, east is row 5, colum 6</p>
   * @return A new {@link TablePosition} east of this {@link TablePosition}
   */  
  @Override
  public Position east() {
    return new TablePosition(y, x+1);
  }

  /**
   * <p>Returns a new {@link TablePosition} with the coordinates
   * to the west of this {@link TablePosition}.</p>
   * <p>From row 5, column 5, west is row 5, colum 4</p>
   * @return A new {@link TablePosition} west of this {@link TablePosition}
   */  
  @Override
  public Position west() {
    return new TablePosition(y, x-1);
  }

  /**
   * Returns a {@link String} representaion of this {@link TablePosition} on the format:
   * <code>(row: 3, column: 5)</code> where:<br>
   * 3 represents the y coordinate and 5 represents the x coordinate
   * @return This {@link TablePosition} as a {@link String} 
   */
  @Override
  public String toString() {
    return "(row: " + y + " column: " + x + ")";
  }
}
