package com.joshuacrotts.tictactoe.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GraphicalBoard extends JPanel {

  private JLabel[] labels;
  
  public GraphicalBoard(TicTacToeGUI gui) {
    int r = gui.getBoard().getRows();
    int c = gui.getBoard().getCols();
    this.setLayout(new GridLayout(r, c));
    this.labels = new JLabel[r * c];
    
    for (int i = 0; i < labels.length; i++) {
      labels[i] = new JLabel("");
      labels[i].setFont(new Font("Arial", Font. PLAIN, 128));
      labels[i].setBorder(new LineBorder(Color.BLACK, 1));
      labels[i].setVerticalAlignment(SwingConstants.CENTER);
      labels[i].setHorizontalAlignment(SwingConstants.CENTER);
      this.add(labels[i], i);
    }
  }
  
  public void clearBoard() {
    for (int i = 0; i < labels.length; i++) {
      this.labels[i].setText(" ");
    }
  }
  
  public void setTextAt(int i, int j, char c) {
    labels[i + j * 3].setText(""+c);
  }
}
