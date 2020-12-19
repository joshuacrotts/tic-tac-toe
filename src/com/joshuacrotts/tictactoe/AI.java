package com.joshuacrotts.tictactoe;

import java.util.HashMap;

import java.util.HashMap;

public class AI {

  /**
   * 
   */
  private Board board;

  /**
   * 
   */
  private HashMap<Character, Integer> score = new HashMap<Character, Integer>();

  /**
   * 
   */
  private char playerChar;
  
  /**
   * 
   */
  private char aiChar;

  public AI(Board board, char aiChar, char playerChar) {
    this.board = board;
    this.aiChar = aiChar;
    this.playerChar = playerChar;
    score.put(aiChar, 10);
    score.put(playerChar, -10);
    score.put('T', 0);
  }

  /**
   * 
   * @return
   */
  public int pickMove() {
    int bestScore = Integer.MIN_VALUE;
    int bestMove = -1;
    int i=0;
    int j=0;
    for (i = 0; i < board.getRows(); i++) {
      for (j = 0; j < board.getCols(); j++) {
        if (board.isEmpty(i, j)) {
          board.placeMove(i, j, aiChar);
          int score = minimax(false, 0);
          board.placeMove(i, j, ' ');
          if (score > bestScore) {
            bestScore = score;
            bestMove = i + j * this.board.getCols();
          }
        }
      }
    }
    
    return bestMove;
  }

  /**
   * 
   * @param isMaximizing
   * @param depth
   * @return
   */
  private int minimax(boolean isMaximizing, int depth) {
    char winner = '\0';
    if (board.hasWinner()) {
      winner = !isMaximizing ? this.aiChar : this.playerChar;
      return this.score.get(winner);
    } else if (board.hasTie()) {
      return this.score.get('T');
    }
    
    return this.minimaxHelper(isMaximizing, depth);
  }

  /**
   * 
   * @param isMaximizing
   * @param depth
   * @return
   */
  private int minimaxHelper(boolean isMaximizing, int depth) {
    int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    for (int i = 0; i < board.getRows(); i++) {
      for (int j = 0; j < board.getCols(); j++) {
        if (board.isEmpty(i, j)) {
          board.placeMove(i, j, isMaximizing ? aiChar : playerChar);
          int score = minimax(!isMaximizing, depth + 1);
          bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
          board.placeMove(i, j, ' ');
        }
      }
    }
    
    return bestScore;
  }
}
