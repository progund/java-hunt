package se.itu.hunt;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import java.awt.*;

import se.itu.hunt.Dimension;

/**
 * An implementation of a FieldDisplay for GUI-based interface.
 */
public class GUIGame implements GameUI {

  static {
    try {
      // Ignore this - it's a fix for Rikard's computer. Hell Dell!
      UIManager.setLookAndFeel((LookAndFeel)Class
                               .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                               .newInstance());
    } catch (Exception ignore) {}
  }
  
  private JTable table;
  private TableModel model;
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

  /**
   * Constructs a new GUI using the supplied Avatar and Board.
   * @param avatar The Avatar for this GUI
   * @param board The Board for this GUI
   */
  public GUIGame(Avatar avatar, Board board) {
    this.player = avatar;
    this.board = board;
    initComponents();
    layoutComponents();
  }
  
  private void initComponents() {
    top = new JPanel(new FlowLayout(FlowLayout.LEADING));
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
    model = new AbstractTableModel() {
        public int getColumnCount() { return dimension.width(); }
        public int getRowCount() { return dimension.height();}
        public Object getValueAt(int row, int col) {
          String symbol=" ";
          if (player.currentPosition().equals(new TablePosition(row, col))) {
            symbol="" + '\u06E9';
          }
          return symbol;
        }
      };
    table = new JTable(model);
    table.setFont(new Font("Serif", Font.BOLD, 40));
    TableColumn column = null;
    for (int i = 0; i < dimension.width(); i++) {
      column = table.getColumnModel().getColumn(i);
      column.setPreferredWidth(10);
    }
    frame = new JFrame("Hunt");
  }
  private void layoutComponents() {
    frame.setLayout(new BorderLayout());
    JPanel topPanel = new JPanel(new GridLayout(1,1));
    topPanel.add(top);
    frame.add(table, BorderLayout.CENTER);
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
                                player.move(Direction.NORTH);
                                //System.out.println(player);
                                //((AbstractTableModel)table.getModel())
                                //  .fireTableCellUpdated(player.currentPosition().x(), player.currentPosition().y());
                                table.revalidate();
                                table.repaint();
                                checkWin();
                                updateButtons();
                              } catch (IllegalMoveException ime) {
                                System.err.println("Illegal move");
                              }
                            });
    south.addActionListener((e) ->
                            { try {
                                player.move(Direction.SOUTH);
                                //System.out.println(player);
                                //((AbstractTableModel)table.getModel())
                                //  .fireTableCellUpdated(player.currentPosition().x(), player.currentPosition().y());
                                table.revalidate();
                                table.repaint();
                                checkWin();
                                updateButtons();
                              } catch (IllegalMoveException ime) {
                                System.err.println("Illegal move");
                              }
                            });
    east.addActionListener((e) ->
                           {
                             try {
                               player.move(Direction.EAST);
                               //System.out.println(player);
                               //((AbstractTableModel)table.getModel())
                               //  .fireTableCellUpdated(player.currentPosition().x(), player.currentPosition().y());
                               table.revalidate();
                               table.repaint();
                               checkWin();
                               updateButtons();
                             } catch (IllegalMoveException ime) {
                               System.err.println("Illegal move");
                             }
                           });
    west.addActionListener((e) ->
                           { try {
                               player.move(Direction.WEST);
                               //System.out.println(player);
                               //((AbstractTableModel)table.getModel())
                               //  .fireTableCellUpdated(player.currentPosition().x(), player.currentPosition().y());
                               table.revalidate();
                               table.repaint();
                               checkWin();
                               updateButtons();
                             } catch (IllegalMoveException ime) {
                               System.err.println("Illegal move");
                             }
                           });   
  }

  private void updateButtons() {
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
        board = new Board(board.dimension());
        player = new Avatar(board, new TablePosition(board.height()/2, board.width()/2));
        System.out.println(player);
        table.repaint();
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
