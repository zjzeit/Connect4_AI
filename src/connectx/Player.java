
package connectx;


/**
 * Sample Player class to demonstrate it for the Connect-X program
 *
 * @author Dennis.Schweitzer
 */

public class Player {
    private int playerNum;                     // which player (1 or 2)
    private int strategyNum;                   // which strategy (1 or 2)

  /**
   * Required constructor to create a new Player
   *
   * @param num - which player number this is
   * @param strategy - which strategy to use
   */
  public Player(int num, int strategy)
  {
      playerNum = num;
      strategyNum = strategy;
  }
 /**
  * makeMove is a required method to make a move given a current board state
  *   only two simple strategies demonstrated - random and next available
  *
  * @param board - current board state
  * @return column number representing move to make
  */
  public int makeMove(Board board)
  {
      BoardEvaluation eval = new BoardEvaluation(playerNum, board, strategyNum);
      return eval.getMove(board, playerNum);
  }
/**
 * randomMove returns a random number between 0 and number of cols - 1 checking
 *   to make sure it is a legal move - it first checks that a legal move is
 *   available to avoid an infinite loop
 *
 * @param currBoard - current state of game
 * @return column for move, or -1 if no legal move
 */
  private int randomMove(Board currBoard){
      // first check that legal move exists
      if(!currBoard.movesRemaining())
          return -1;          // no legal move exists
      // random number between 0 and number of columns - 1
      int move = (int)(Math.random() * currBoard.getNumCols());
      // check if legal, if not, keep generating random move until it is
      while(!currBoard.legalMove(move))
          move = (int)(Math.random() * currBoard.getNumCols());
      return move;

  }
  /**
   * nextAvailable is a strategy that returns the first column (starting from
   *   0) that is a legal move
   *
   * @param currBoard - current board state
   * @return column number to move to, or -1 if no legal move
   */
  private int nextAvailable(Board currBoard){
      for(int col = 0; col < currBoard.getNumCols(); col++)
          if(currBoard.legalMove(col))
              return col;
      return -1;
  }
}
