
package connectx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Board represents the current state of the Connect-X game.  It contains all
 *   of the necessary attributes to play the game, including the board, and
 *   several utility methods to access them and perform various operations.
 *
 * @author Dennis.Schweitzer
 */
public class Board extends JPanel{
    private int[][] gameBoard;    // 2-D array of board, 0=empty 1/2=player
    private int numRows;          // number of rows in game board
    private int numCols;          // number of cols in game board
    private int numInARow;        // necessary number in a row to win
    private int maxLookAhead;     // maximum number of look-ahead moves to play
 private Graphics g1;
/**
 * Constructor to create initial board with all necessary parameters
 * 
 * @param rows - number of rows
 * @param cols - number of cols
 * @param rowCnt - number needed in a row to win
 * @param maxAhead - maximum number of look ahead moves
 */
  public Board(int rows, int cols, int rowCnt, int maxAhead){
      setSize(500,500);        // size of panel to draw game
      numCols = cols;          // initialize attributes
      numRows = rows;
      numInARow = rowCnt;
      maxLookAhead = maxAhead;
      gameBoard = new int[rows][cols];  // set size of board array
  }
  public void updateBoard(int rows, int cols, int rowCnt, int maxAhead){
      numCols = cols;          // initialize attributes
      numRows = rows;
      numInARow = rowCnt;
      maxLookAhead = maxAhead;
      gameBoard = new int[rows][cols];  // set size of board array
      repaint();
  }

  /**
   * getNumRows returns the number of rows
   * 
   * @return number of rows
   */
  public int getNumRows(){
      return numRows;
  }

  /**
   * getNumCols returns the number of cols
   *
   * @return number of cols
   */
  public int getNumCols(){
      return numCols;
  }

  /**
   * getNumInARow returns the number in a row needed to win
   *
   * @return number in a row
   */
  public int getNumInARow(){
      return numInARow;
  }

  /**
   * getLookAhead returns the number of moves allows to look ahead
   *
   * @return number of moves to look ahead
   */
  public int getLookAhead(){
      return maxLookAhead;
  }

   /**
   * getBoard returns the 2D array representing the board
   *
   * @return game board
   */
  public int[][] getBoard(){
      return gameBoard;
  }
   /**
   * setBoard sets the 2D array representing the board
   *
   */
  public void setBoard(int[][] board){
      for(int i=0;i<numRows;i++)
          for(int j=0;j<numCols;j++)
              gameBoard[i][j] = board[i][j];
  }
 /**
 * addMove adds a new move to the board
 *
 * @param col - which column to put move in
 * @param player - which player made the move
 * @return true if legal move, false otherwise
 */
  public boolean addMove(int col, int player){
      // first check if room to make move
      if(gameBoard[numRows-1][col] != 0)
          return false;   // illegal move
      int row = 0;
      // find first empty row
      while(gameBoard[row][col] != 0)
          row++;
      // set first empty to player
      gameBoard[row][col] = player;
      repaint();
      return true;
  }
  /**
   * clearBoard removes all moves from the board
   */
  public void clearBoard(){
      for(int row = 0; row < numRows; row++)
          for(int col =0; col < numCols;col++)
              gameBoard[row][col] = 0;
      repaint();
  }
  /**
   * removeBottomRow is used for tetris mode and removes the first row
   */
  public void removeBottomRow(){
      // first make sure the bottom row is full (no empty spots)
      boolean rowFull = true;
      for(int i=0;i<numCols;i++)
          rowFull = rowFull && gameBoard[0][i] != 0;
      //if it is, move every row down one, and set top row to empty
      if(rowFull)
          for(int col=0;col<numCols;col++){
              for(int row = 0;row < numRows-1;row++)
                  gameBoard[row][col] = gameBoard[row+1][col];
              gameBoard[numRows-1][col] = 0;
          }
  }
  /**
   * paintComponent redraws the panel based on the current game state
   *
   * @param g - Graphics object used for drawing
   */
    @Override
    public void paintComponent(Graphics g) {
        g1 = g;
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        // draw black lines for game board
        g.fillRect(250-numCols*23,50+40*numRows,numCols*46+6,6);
        for(int col=0;col <= numCols;col++)
            g.fillRect(250-numCols*23+46*col,50,6,40*numRows);
        // draw player1 and player2 pieces
        for(int row=0;row < numRows;row++)
            for(int col=0;col<numCols;col++){
              if(gameBoard[row][col] == 1){
                  g.setColor(Color.RED);
                  g.fillOval(255-numCols*23+46*col, 10+40*(numRows)-row*40, 40,40);
              }
              else if(gameBoard[row][col] == 2){
                  g.setColor(Color.BLUE);
                  g.fillOval(255-numCols*23+46*col, 10+40*(numRows)-row*40, 40,40);
              }

            }
    }
    /**
     * checkWin checks current game board to see if a player has won
     *
     * @return winning player number, 0 if a draw, -1 if no winner yet
     */
    public int checkWin(){
        // for each board position
        for(int row = 0;row < numRows;row++)
            for(int col=0;col<numCols;col++) {
                if(gameBoard[row][col] != 0){
                  // check up
                  if(row <= numRows-numInARow ){
                      int cnt = 0;
                      for(int i=1;i<numInARow;i++)
                          if(gameBoard[row+i][col] == gameBoard[row][col])
                              cnt++;
                      if(cnt == numInARow-1)
                          return gameBoard[row][col];
                  }
                  // check right
                  if(col <= numCols-numInARow){
                      int cnt = 0;
                      for(int i=1;i<numInARow;i++)
                          if(gameBoard[row][col+i] == gameBoard[row][col])
                              cnt++;
                      if(cnt == numInARow-1)
                          return gameBoard[row][col];
                  }
                  // check right-up
                  if(row <= numRows-numInARow && col <= numCols-numInARow){
                      int cnt = 0;
                      for(int i=1;i<numInARow;i++)
                          if(gameBoard[row+i][col+i] == gameBoard[row][col])
                              cnt++;
                      if(cnt == numInARow-1)
                          return gameBoard[row][col];
                  }
                  // check left-up
                  if(row <= numRows-numInARow && col >= numInARow-1){
                      int cnt = 0;
                      for(int i=1;i<numInARow;i++)
                          if(gameBoard[row+i][col-i] == gameBoard[row][col])
                              cnt++;
                      if(cnt == numInARow-1)
                          return gameBoard[row][col];
                  }
                }
            }
        if(!movesRemaining())
            return 0;  // if no winner and no moves remaining, the game is a draw
        // return no winner yet
        return -1;
    }
    /**
     * movesRemaining checks to make sure there is a legal move left
     *
     * @return true if legal move, false otherwise
     */
    public boolean movesRemaining(){
        for(int col = 0;col < numCols; col++)
            if(gameBoard[numRows-1][col] == 0)
                return true;
        return false;
    }
    /**
     * mouseToCol converts a mouse X position to column number for the game -
     *   used when a human is playing
     *
     * @param x - mouse X
     * @return column number, or -1 if not a legal column
     */
    public int mouseToCol(int x){
        x = (x-(256-numCols*23)) / 46;
        if(x >= 0 && x < numCols)
            return x;
        else
            return -1;
    }
    /**
     * legalMove checks whether a proposed move (col number) is legal by
     *   checking whether the top row is empty
     *
     * @param col - proposed move
     * @return true if legal, false otherwise
     */
    public boolean legalMove(int col){
        return (col >= 0 && col < numCols && gameBoard[numRows-1][col] == 0);
    }
  public void redraw(){
      paintComponent(g1);
  }
 
}
  
