package com.joshuacrotts.tictactoe;

public class Board {
  
  private final int WIN_COUNT = 3;

  /**
   * 2D array of chars for the board.
   */
  private char[][] board;

  /**
   * Number of rows.
   */
  private int rows;

  /**
   * Number of columns.
   */
  private int cols;
  
  /**
   * Variable dictating whose turn it is.
   */
  private int turn = 0;

  public Board(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.board = new char[rows][cols];
    this.newGame();
  }
  
  /**
   * 
   */
  public void newGame() {
    this.initializeBoard();
    this.setTurn(0);
  }

  /**
   * 
   * @param i
   * @param j
   * @param c
   */
  public void placeMove(int i, int j, char c) {
    board[i][j] = c;
  }
  
  /**
   * 
   * @param i
   * @param j
   * @return
   */
  public boolean isEmpty(int i, int j) {
    return board[i][j] == ' ';
  }
  
  /**
   * 
   * @return
   */
  public boolean isBoardEmpty() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] != ' ') {
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * 
   * @return
   */
  public boolean isBoardFull() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == ' ') {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 
   */
  public void printBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  /**
   * 
   * @return
   */
  public boolean hasTie() {
    return isBoardFull() && !this.hasWinner();
  }
  
  public boolean hasWinner() {
    return this.topRowWinner() || this.middleRowWinner() || this.bottomRowWinner() || this.leftColWinner()
        || this.middleColWinner() || this.rightColWinner() || this.leftDiagonalWinner() || this.rightDiagonalWinner();
  }

  private boolean topRowWinner() {
    return board[0][0] != ' ' && board[0][0] == board[0][1] && board[0][1] == board[0][2];
  }

  private boolean middleRowWinner() {
    return board[1][0] != ' ' && board[1][0] == board[1][1] && board[1][1] == board[1][2];
  }

  private boolean bottomRowWinner() {
    return board[2][0] != ' ' && board[2][0] == board[2][1] && board[2][1] == board[2][2];
  }

  private boolean leftColWinner() {
    return board[0][0] != ' ' && board[0][0] == board[1][0] && board[1][0] == board[2][0];
  }

  private boolean middleColWinner() {
    return board[0][1] != ' ' && board[0][1] == board[1][1] && board[1][1] == board[2][1];
  }

  private boolean rightColWinner() {
    return board[0][2] != ' ' && board[0][2] == board[1][2] && board[1][2] == board[2][2];
  }

  private boolean leftDiagonalWinner() {
    return board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2];
  }

  private boolean rightDiagonalWinner() {
    return board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0];
  }

  private void initializeBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = ' ';
      }
    }
  }
  
  public char[][] getBoard() {
    return this.board;
  }
  
  public int getRows() {
    return this.rows;
  }
  
  public int getCols() {
    return this.cols;
  }
  
  public void setTurn(int t) {
    this.turn = t;
  }
  
  public int getTurn() {
    return this.turn;
  }
}
