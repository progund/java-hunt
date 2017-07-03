package se.itu.hunt;

/**
 * <p>
 *  Hunt - A simple silly game<br>
 *  Ported to Java by Rikard,<br>
 *  Original author of the C version: hajo<br>
 *  C programming language version here:
 *  http://www.cse.chalmers.se/edu/year/2009/course/LEU480/lect/ch5/sillyhunt.c
 * </p>
 * <p>
 * To run:
 * </p>
 * <pre>
 * $ ./run.sh --text
 * </pre>
 * <p>
 * If you want to run it "manually":
 * </p>
 * <pre>
 * $ java -cp bin se.itu.hunt.MainTextGame
 * </pre>
 */
public class MainTextGame {

  public static void main(String[] args) {
    // Set up the game
    Board board = new Board(new Dimension(5,5));
    Avatar player = new Avatar(board, new Position(board.width()/2, board.height()/2));
    GameUI game = new TextGame(player, board);
    game.displayAndRun();
  }
    
}
