package se.itu.hunt;

public class Dimension {
  private int width;
  private int height;
  public Dimension(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int width() { return width; }

  public int height() { return height; }
  
  @Override
  public String toString() {
    return "(" + width + " X " + height + ")";
  }

}
