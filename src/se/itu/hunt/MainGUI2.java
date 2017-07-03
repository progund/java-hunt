package se.itu.hunt;

public class MainGUI2 {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          // Set up the game:
          Dimension dimension = new Dimension(10,5);
          Board board = new Board(dimension);
          Avatar player = new Avatar(board, new GridPosition(dimension.height()/2, dimension.width()/2));
          System.out.println("Initial player position: " + player);
          GameUI gui = new GUIGame2(player, board);
          gui.displayAndRun();
        }
      });
  }
}
