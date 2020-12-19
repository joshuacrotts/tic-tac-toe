package com.joshuacrotts.tictactoe;

public class TicTacToe {

  /**
   * Number of rows for the Tic-Tac-Toe board.
   */
  private final int ROWS = 3;

  /**
   * Number of columns for the Tic-Tac-Toe board.
   */
  private final int COLS = 3;
  
  /**
   * Number of cells required to win.
   */
  private final int WIN_COUNT = 3;

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

  public TicTacToe() {
    this.board = new Board(ROWS, COLS);
    this.playerOne = new AI(board, playerOneChar, playerTwoChar);
    this.playerTwo = new AI(board, playerTwoChar, playerOneChar);
    this.playGame();
  }

  public void playGame() {
    while (!board.hasWinner()) {
      if (board.hasTie()) {
        board.newGame();
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
      board.printBoard();
      System.out.println("-------------");
      board.setTurn(board.getTurn() + 1);
    }
    board.printBoard();
  }
  
  public Board getBoard() {
    return this.board;
  }
}
