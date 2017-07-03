package se.itu.hunt;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import java.awt.*;

import se.itu.hunt.Dimension;

/**
 * An implementation of a GameUI for GUI-based interface.
 */

public class GUIGame2 implements GameUI {

  static {
    try {
      // Ignore this - it's a fix for Rikard's computer. Hell Dell!
      UIManager.setLookAndFeel((LookAndFeel)Class
                               .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                               .newInstance());
    } catch (Exception ignore) {}
  }
  
  private JFrame frame;
  private Avatar player;
  private Dimension dimension;
  private Board board;
  private JPanel top;
  private JPanel buttons;
  private JButton north;
  private JButton south;
  private JButton east;
  private JButton west;
  private int numMoves;  
  private final char avatarSymbol = '\u06E9';
  
  private JButton[][] buttonMatrix;
  private JPanel boardPanel;
  
  /**
   * Constructs a new GUI using the supplied Avatar and Board.
   * @param avatar The Avatar for this GUI
   * @param board The Board for this GUI
   */
  public GUIGame2(Avatar avatar, Board board) {
    this.player = avatar;
    this.board = board;
    initComponents();
    layoutComponents();
  }
  
  private void initComponents() {
    top = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttons = new JPanel(new GridLayout(3,3));
    JPanel blank = new JPanel();
    north = new JButton("North");
    south = new JButton("South");
    east = new JButton("East");
    west = new JButton("West");
    buttons.add(blank);
    blank = new JPanel();
    buttons.add(north);
    buttons.add(blank);
    blank = new JPanel();
    buttons.add(west);
    buttons.add(blank);
    blank = new JPanel();
    buttons.add(east);
    buttons.add(blank);
    blank = new JPanel();
    buttons.add(south);
    buttons.add(blank);    
    top.add(buttons);
    
    dimension = board.dimension();
    buttonMatrix = new JButton[dimension.height()][dimension.width()];

    initBoard();
    //table.setFont(new Font("Serif", Font.BOLD, 40));
    
    frame = new JFrame("Hunt");
  }

  private void initBoard() {
    boardPanel = new JPanel
      (new GridLayout(dimension.height(), dimension.width()));
    for (int x = 0; x < dimension.height(); x++) {
      for (int y = 0; y < dimension.width(); y++) {
        System.out.println("Adding button at [" + x + "][" + y + "]"); 
        if(player.currentPosition().y() == x &&
           player.currentPosition().x() == y) {
          buttonMatrix[x][y] = new JButton("" + avatarSymbol);
          buttonMatrix[x][y].setText("" + avatarSymbol);
          buttonMatrix[x][y].setFont(new Font("Serif", Font.BOLD, 60));
        } else {
          //buttonMatrix[x][y] = new JButton("[" + x + "][" + y + "]");
          buttonMatrix[x][y] = new JButton("");
          buttonMatrix[x][y].setFont(new Font("Serif", Font.BOLD, 60));
        }
        if (boardPanel.getComponentCount() < dimension.height() * dimension.width()) {
          boardPanel.add(buttonMatrix[x][y]);
        }
      }
    }
    boardPanel.revalidate();
    boardPanel.repaint();
    boardPanel.updateUI();
  }

  private void layoutComponents() {
    frame.setLayout(new BorderLayout());
    JPanel topPanel = new JPanel(new GridLayout(1,1));
    topPanel.add(top);
    frame.add(boardPanel, BorderLayout.CENTER);
    frame.add(topPanel, BorderLayout.NORTH);
    frame.pack();
    addListeners();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Cheat:
    System.out.println(board);
    System.out.println("Treasure is at row: " + board.treasurePosition().y() +
                       " column: " + board.treasurePosition().x());
  }

  /**
   * Displays this GUI
   */
  public void displayAndRun() {
    frame.setVisible(true);
  }

  private void addListeners() {
    
    north.addActionListener((e) ->
                            { try {
                                setButtonText(player.currentPosition().y(),
                                              player.currentPosition().x(),
                                              "");
                                System.out.println(player);
                                player.move(Direction.NORTH);
                                System.out.println(player);
                                updateButtons();
                                checkWin();
                              } catch (IllegalMoveException ime) {
                                System.err.println("Illegal move");
                              }
                            });
    south.addActionListener((e) ->
                            { try {
                                setButtonText(player.currentPosition().y(),
                                              player.currentPosition().x(),
                                              "");
                                player.move(Direction.SOUTH);
                                updateButtons();
                                checkWin();
                              } catch (IllegalMoveException ime) {
                                System.err.println("Illegal move");
                              }
                            });
    east.addActionListener((e) ->
                           {
                             try {
                                setButtonText(player.currentPosition().y(),
                                              player.currentPosition().x(),
                                              "");
                               player.move(Direction.EAST);
                               updateButtons();
                               checkWin();
                             } catch (IllegalMoveException ime) {
                               System.err.println("Illegal move");
                             }
                           });
    west.addActionListener((e) ->
                           { try {
                                setButtonText(player.currentPosition().y(),
                                              player.currentPosition().x(),
                                              "");
                               player.move(Direction.WEST);
                               updateButtons();
                               checkWin();
                             } catch (IllegalMoveException ime) {
                               System.err.println("Illegal move");
                             }
                           });   
  }

  private void setButtonText(int row, int col, String text) {
    System.out.println("attempting to set [" + row + "]["+col+"] to "+text); 
    buttonMatrix[row][col].setText(text);
  }
  private void updateButtons() {
    System.out.println("Player: " + player);
    setButtonText(player.currentPosition().y(),
                  player.currentPosition().x(),
                  "" + avatarSymbol);
    north.setEnabled(player.currentPosition().y() != 0);
    south.setEnabled(player.currentPosition().y() != board.height()-1);
    west.setEnabled( player.currentPosition().x() != 0);
    east.setEnabled( player.currentPosition().x() != board.width()-1);
    
  }
  
  private void checkWin() {
    numMoves++;
    if (player.foundIt()) {      
      int answer = JOptionPane.showConfirmDialog(frame, "Congratulations, You have won!\nNumber of moves: " + numMoves +
                        "\n Do you want to play again?",
                        "Epic win",
                        JOptionPane.YES_NO_OPTION);

      if (answer == JOptionPane.YES_OPTION) {
        setButtonText(player.currentPosition().y(),
                      player.currentPosition().x(),
                      "");
        board = new Board(board.dimension());
        player = new Avatar(board, new TablePosition(board.height()/2, board.width()/2));
        System.out.println(player);
        updateButtons();
        //table.repaint();
        initBoard();
        frame.add(boardPanel,BorderLayout.CENTER);
        boardPanel.revalidate();
        boardPanel.repaint();
        frame.repaint();
        numMoves = 0;
        System.out.println(board);
        System.out.println("Treasure is at row: " + board.treasurePosition().y() +
                           " column: " + board.treasurePosition().x());
      } else {
        System.exit(0);
      }
    }
  }
}
