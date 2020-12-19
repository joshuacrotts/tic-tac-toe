package com.joshuacrotts.tictactoe.gui;

import java.awt.event.KeyEvent;

import com.joshuacrotts.tictactoe.AI;
import com.joshuacrotts.tictactoe.Board;
import com.theta.input.Command;
import com.theta.platform.ThetaSwingApplication;

public class TicTacToeGUI extends ThetaSwingApplication {

  /**
   * Number of rows for the Tic-Tac-Toe board.
   */
  private final int ROWS = 3;

  /**
   * Number of columns for the Tic-Tac-Toe board.
   */
  private final int COLS = 3;

  /**
   * Graphical board object.
   */
  private GraphicalBoard gui;

  /**
   * Board object.
   */
  private Board board;

  /**
   * First AI player.
   */
  private AI playerOne;

  /**
   * Second AI player.
   */
  private AI playerTwo;

  /**
   * Character that belongs to the first player.
   */
  private char playerOneChar = 'X';

  /**
   * Character that belongs to the second player.
   */
  private char playerTwoChar = 'O';

  /**
   * 
   */
  private IncreaseSimSpeed incSpeed;

  /**
   * 
   */
  private DecreaseSimSpeed decSpeed;

  public TicTacToeGUI() {
    super(600, 600, 5, "Tic-Tac-Toe");
    this.board = new Board(ROWS, COLS);
    this.gui = new GraphicalBoard(this);
    this.playerOne = new AI(board, playerOneChar, playerTwoChar);
    this.playerTwo = new AI(board, playerTwoChar, playerOneChar);
    this.incSpeed = new IncreaseSimSpeed(this);
    this.decSpeed = new DecreaseSimSpeed(this);
    super.addComponent(this.gui);
    super.setVisible(true);
  }

  @Override
  public void run() {
    if (board.hasTie()) {
      board.newGame();
      gui.clearBoard();
    } else if (board.hasWinner()) {
      System.exit(1);
    }

    char c = board.getTurn() % 2 == 0 ? playerOneChar : playerTwoChar;
    int row = -1;
    int col = -1;
    int move = -1;

    if (board.getTurn() % 2 != 0) {
      move = playerTwo.pickMove();
      row = move % ROWS;
      col = move / COLS;
    } else {
      if (board.getTurn() == 0) {
        row = (int) (Math.random() * ROWS);
        col = (int) (Math.random() * COLS);
      } else {
        move = playerOne.pickMove();
        row = move % ROWS;
        col = move / COLS;
      }
    }

    board.placeMove(row, col, c);
    gui.setTextAt(row, col, c);
    board.setTurn(board.getTurn() + 1);
  }

  /**
   * 
   * @author joshuacrotts
   *
   */
  private class IncreaseSimSpeed extends Command {

    private TicTacToeGUI gui;

    public IncreaseSimSpeed(TicTacToeGUI gui) {
      this.gui = gui;
      this.bind(this.gui.getKeyboard(), KeyEvent.VK_W);
    }

    @Override
    public void pressed(float dt) {
      this.gui.setFPS(this.gui.getFPS() + 1);
    }
  }

  /**
   * 
   * @author joshuacrotts
   *
   */
  private class DecreaseSimSpeed extends Command {

    private TicTacToeGUI gui;

    public DecreaseSimSpeed(TicTacToeGUI gui) {
      this.gui = gui;
      this.bind(this.gui.getKeyboard(), KeyEvent.VK_S);
    }

    @Override
    public void pressed(float dt) {
      if (this.gui.getFPS() > 0)
        this.gui.setFPS(this.gui.getFPS() - 1);
    }
  }

  public Board getBoard() {
    return this.board;
  }
}
