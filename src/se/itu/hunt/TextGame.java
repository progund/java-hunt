package se.itu.hunt;

import java.io.*;

/**
 * An implementaion of a {@link GameUI} for a text-based interface.
 */
public class TextGame implements GameUI {
  /* Player symbol */
  private static final char PLAYER_SYMBOL='\u06E9';

  private Avatar player;
  private Board board;

  /* Menu */
  private static final char NORTH     = 'n';
  private static final char EAST      = 'o';
  private static final char SOUTH     = 's';
  private static final char WEST      = 'v';
  private static final char EXIT      = 'q';
  private static final char CHEAT     = 'f';
  
  /**
   * Constructs a new {@link TextGame} with an {@link Avatar}
   * and a {@link Board}.
   * @param avatar The {@link Avatar} for this {@link TextGame}
   * @param board The {@link Board} for this {@link TextGame}
   */
  public TextGame(Avatar avatar, Board board) {
    this.board = board;
    this.player = avatar;
  }

  @Override
  public void displayAndRun() {
    try {
      play();
    } catch (IllegalMoveException ime) {
      System.err.println("Illegal move: " + ime.getMessage());
    } catch (IOException ioe) {
      System.err.println("Problem reading from user: " + ioe.getMessage());
    }
  }
  private void play() throws IllegalMoveException, IOException {
    boolean playAgain = true;
    boolean hasQuit = false;
    int numMoves = 0;
    char action = '\0';
    
    while (playAgain) {
      hasQuit = false;
      //System.out.println("Board: " + board);
      //System.out.println("Avatar: " + player);
      numMoves = 0;
      while (!player.foundIt() && !hasQuit) {
        System.out.println("Antal drag: "+numMoves);
        displayBoard();
        printInstructions();
        /* Read user action */
        action = readAction();
        switch( action ){
          case NORTH:
            if (player.currentPosition().y() < (board.height() - 1)) {
              player.move(Direction.NORTH);
            }
            break;
          case SOUTH:
            if (player.currentPosition().y() > 0){
              player.move(Direction.SOUTH);
            }
            break; 
          case EAST:
            if (player.currentPosition().x() < (board.width() - 1)) {
              player.move(Direction.EAST);
            }
            break;
          case WEST:
            if (player.currentPosition().x() > 0) {
              player.move(Direction.WEST);
            }
            break;       
          case CHEAT:
            System.out.printf("Skatten har koordinaten %s\n", board.treasurePosition());
            break;
          case EXIT: hasQuit = true;
            break;  
          default: ;
        }
        numMoves++;		
        System.out.println("Du befinner dig på koordinaten " + player.currentPosition() + ")");
        if( player.foundIt() ){
          displayBoard();
          System.out.println("Grattis du hittade skatten!!");
          System.out.println("Det tog " + numMoves + " drag.");
        }
        if( hasQuit ){
          System.out.println("Omgången avslutas efter "+numMoves+" drag.");
        }		
      }
      System.out.print("Vill du spela igen (J/n)? ");  
      action = readAction();
      if( action == 'n' || action == 'N'){
        playAgain=false;
      }
      board = new Board(board.dimension());
      player = new Avatar(board, new Position(board.width()/2, board.height()/2));
    }
    System.out.println("Bye!");
  }

  /* Reader for characters */    
  private char readAction() throws IOException {
    InputStreamReader isr = new InputStreamReader(System.in);
    char result = 'q';
    result = (char)isr.read();
    return result;
  }

    /* Prints instructions */
  private void printInstructions(){
    System.out.println("Ange vädersträck:");
    System.out.println("   'n'");
    System.out.println("'v'   'o'");
    System.out.println("   's'");
    System.out.println("Ange 'q' för att ge upp spelomgången.");
    System.out.print  ("Ange ditt val: ");	      
  }

  /**
   * Displays this {@link TextGame}
   */
  private void displayBoard() {
    Position p = null;
    int MAX_POS_Y = board.height() - 1;
    int MIN_POS_Y = 0;
    int MAX_POS_X = board.width() - 1;
    int MIN_POS_X = 0;
    
    for (int y = MAX_POS_Y; y >= MIN_POS_Y; y--) {
      System.out.print(y + " ");
      for(int x = MIN_POS_X; x <= MAX_POS_X; x++) {
        p = new Position(x,y);
        if (p.equals(player.currentPosition())) {
          if (board.isTreasureAt(p)) {
            System.out.print("$ ");
          } else {
            System.out.print(PLAYER_SYMBOL + " ");
          }
        } else {
          System.out.print("_ ");
        }        
      }
      System.out.println();
    }
    // Print the x values
    System.out.print("  ");
    for(int x = MIN_POS_X; x <= MAX_POS_X; x++) {
      System.out.print(x + " ");
    }
    System.out.println();
  }
}
