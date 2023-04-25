
// Triveni Mukund Puri


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
  private JFrame frame;
  private JButton[][] grid;
  private JLabel resultLabel;
  private int currentPlayer;
  private int numMoves;
  private int[][] scoreTable;
  private JButton restartButton;

  public TicTacToe() {
    frame = new JFrame("Tic Tac Toe");
    grid = new JButton[3][3];
    resultLabel = new JLabel(" ");
    currentPlayer = 1;
    numMoves = 0;
    scoreTable = new int[3][3];
    restartButton = new JButton("Restart Game");

    JPanel gridPanel = new JPanel(new GridLayout(3, 3));
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        JButton button = new JButton();
        button.addActionListener(this);
        grid[row][col] = button;
        gridPanel.add(button);
      }
    }

    JPanel bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.add(resultLabel, BorderLayout.CENTER);
    bottomPanel.add(restartButton, BorderLayout.EAST);

    restartButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        resetGame();
      }
    });

    frame.add(gridPanel, BorderLayout.CENTER);
    frame.add(bottomPanel, BorderLayout.SOUTH);

    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton)e.getSource();
    int row = -1, col = -1;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (button == grid[i][j]) {
          row = i;
          col = j;
          break;
        }
      }
    }

    if (row == -1 || col == -1) {
      return;
    }

    if (!button.getText().equals("")) {
      return;
    }

    numMoves++;
    button.setText(currentPlayer == 1 ? "X" : "O");
    scoreTable[row][col] = currentPlayer;

    if (checkWin()) {
      resultLabel.setText("Player " + currentPlayer + " wins!");
      disableGrid();
    } else if (checkTie()) {
      resultLabel.setText("Tie game!");
      disableGrid();
    } else {
      currentPlayer = currentPlayer == 1 ? 2 : 1;
      resultLabel.setText("Player " + currentPlayer + "'s turn");
    }
  }

  private boolean checkWin() {
    // Check rows
    for (int row = 0; row < 3; row++) {
      if (scoreTable[row][0] == currentPlayer
          && scoreTable[row][1] == currentPlayer
          && scoreTable[row][2] == currentPlayer) {
        return true;
      }
    }

    // Check columns
    for (int col = 0; col < 3; col++) {
      if (scoreTable[0][col] == currentPlayer
          && scoreTable[1][col] == currentPlayer
          && scoreTable[2][col] == currentPlayer) {
        return true;
      }
    }

    // Check diagonals
    if (scoreTable[0][0] == currentPlayer
        && scoreTable[1][1] == currentPlayer
        && scoreTable[2][2] == currentPlayer) {

          return true;
        }

        if (scoreTable[0][2] == currentPlayer
            && scoreTable[1][1] == currentPlayer
            && scoreTable[2][0] == currentPlayer) {
          return true;
        }

        return false;
      }

      private boolean checkTie() {
        return numMoves == 9;
      }

      private void disableGrid() {
        for (int row = 0; row < 3; row++) {
          for (int col = 0; col < 3; col++) {
            grid[row][col].setEnabled(false);
          }
        }
      }

      private void resetGame() {
        for (int row = 0; row < 3; row++) {
          for (int col = 0; col < 3; col++) {
            grid[row][col].setText("");
            grid[row][col].setEnabled(true);
            scoreTable[row][col] = 0;
          }
        }

        currentPlayer = 1;
        numMoves = 0;
        resultLabel.setText("Player 1's turn");
      }

      public static void main(String[] args) {
        new TicTacToe();
      }
    }
