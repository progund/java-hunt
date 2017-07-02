package se.itu.hunt;

/**
 *  Hunt - A simple silly game
 *  Ported to Java by Rikard,
 *  Original author of the C version: hajo
 *  C programming language version here:
 *  http://www.cse.chalmers.se/edu/year/2009/course/LEU480/lect/ch5/sillyhunt.c
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
