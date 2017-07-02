package se.itu.hunt;

public class MainGUI {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          // Set up the game:
          Dimension dimension = new Dimension(5,10);
          Board board = new Board(dimension);
          Avatar player = new Avatar(board, new TablePosition(dimension.height()/2, dimension.width()/2));
          GameUI gui = new GUIGame(player, board);
          gui.displayAndRun();
        }
      });
  }
}
