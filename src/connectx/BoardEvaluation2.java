
package connectx;

/**
 *
 * @author C13Zachary.Zeitlin
 */
public class BoardEvaluation2
{
    //int bestMove = 0;       // column that is the best move
    //double max = -1;   // best board evaluation (initially worst)
    private int me;             // the current player this object represent
    private int strategy;       // the strategy number to use (1 or 2)
    private double boardVals[][];   // the value of each position on the board.

    /**
     * Constructor for the BoardEvaluation object which is used to evaluate a
     * connect-x board.
     * @param player The player number (1 or 2) that owns the current object
     * @param board The board.
     * @param strategy The strategy number to use (1 or 2).
     */
    public BoardEvaluation2(int player, Board board, int strategy)
    {
        /* Board is essentially a CONE function: center of the board = greatest
         * value.
         * Src for cone equation: http://www.wolframalpha.com/input/?i=cone
         * New function used, corners = 0, and the value of each position on
         * the board increases as you move toward the center of the board.
         */
        double halfRows = ((double)board.getNumRows()+1)/2;
        double halfCols = ((double)board.getNumCols()+1)/2;

        this.strategy = strategy;
        me = player;
        boardVals = new double[board.getNumRows()][board.getNumCols()];
        for(int row = 0; row < board.getNumRows(); row++)
            for(int col = 0; col < board.getNumCols(); col++)
            {
                boardVals[row][col] = (( halfRows  -  (Math.abs(halfRows-row-1)))+
                                      ( halfCols  -  (Math.abs(halfCols-col-1))))/100;
            }

        /*
        //display eval board
        for (int i =0; i < boardVals.length; i++) {
        for (int j = 0; j < boardVals[0].length; j++) {
        System.out.print("   " + boardVals[i][j]);
        }
        System.out.println("");
        }

         */



    }

    /**
     * Evaluates a given board to a double value -1 < value < 1
     * @param board the board position to evaluate.
     * @param playerNum The player for whom the board is in favor of (if the
     * board is a winning board for the given player, evaluate will return 1.
     * @return double representation of the board's evaluation. If 1, this board
     * is a win for the given player, if -1, this board is a loss for the given
     * player.
     */
    public double evaluate(Board board, int playerNum)
    {
        double eval = 0;
        if(board.checkWin() > 0)
            if(board.checkWin() == playerNum)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        for(int row = 0; row < board.getNumRows(); row++)
            for(int col = 0; col < board.getNumCols(); col++)
            {
                if(board.getBoard()[row][col] == playerNum)
                    eval += boardVals[row][col];
                else if(board.getBoard()[row][col] != 0)
                    eval -= boardVals[row][col];
            }

        // do some extra calculations if strategy = 2
        // note: takes much longer
        if(strategy == 2){
        eval += getThreatCount(board, playerNum)*.09;
        }

        return eval;
    }

    /**
     * Finds the number of "threats" that the given player contains, and subtracts
     * this value from the number of "threats" that the opposing player contains.
     * A threat is defined as any place on the board where placing a move at a
     * specific coordinate results in a victory.
     * @param board The board to analize.
     * @param player The player to evaluate for.
     * @return An integer representation of the net threats you contain compared
     * to your opponent.
     */
    public int getThreatCount(Board board, int player)
    {
        Board tempBoard;
        int[][] gameBoard = board.getBoard();
        int count = 0;
        for(int row = 0; row < board.getNumRows(); row++)
            for(int col = 0; col < board.getNumCols(); col++)
            {
                if(gameBoard[row][col]==0)
                {
                    int temp = gameBoard[row][col];
                    gameBoard[row][col] = player;   // add "pretend" move

                    tempBoard = clone(board);
                    tempBoard.setBoard(gameBoard);
                    if(tempBoard.checkWin() == player)
                        count++;
                    if(tempBoard.checkWin() == player%2 + 1)
                        count--;

                    gameBoard[row][col] = temp; // undo "pretend" move
                }
            }
        return count;
    }

    /**
     * Chooses a move to make from the given board position.
     * @param board the board position to decide from.
     * @param playerNum the player that receives a positive evaluation.
     * @return the column representing the move.
     */
    public int getMove(Board board, int playerNum)
    {
        double bestValue = -1; // initialize best value to lowest value

        GameTree gameTree = new GameTree(board);
        GameTreeNode bestNode = new GameTreeNode(board,0);
        //populateTree(gameTree.getRoot(), 0, playerNum);

        // populate first set of children.
        for(int x = 0; x < board.getNumCols(); x++)
        {
                Board clone = clone(board);
                if(clone.legalMove(x))
                {
                    clone.addMove(x,playerNum);
                    gameTree.getRoot().addChild(new GameTreeNode(clone,x));
                }
        }
        for(GameTreeNode x : gameTree.getRoot().getChildren()) // find best eval
        {
            //double tempVal = nodeValue(x,1);
            double tempVal = alphaBeta(x, -2, 2, playerNum, 1);
            System.out.print(tempVal+", ");
            if(tempVal >= bestValue)
            {
                bestValue = tempVal;
                bestNode = x;
            }
        }
        System.out.println("");
        return bestNode.getLastMove();
    }

    /**
     * Performs a minimax algorithm to determine which move is best.
     * @param node the board position to decide from.
     * @param level The level of the minimax algorithm analysis.
     * @return the column representing the move.
     */

    public double nodeValue(GameTreeNode node, int level)
    {
        if(node.isLeaf())
            return node.getValue();
        if(level % 2 == 0)  // if level is even
        {
            double max = -1;        // initialize max to lowest value
            for(GameTreeNode child : node.getChildren())
            {
                double maxTemp = nodeValue(child, level+1);
                if(maxTemp > max)
                    max = maxTemp;
            }
            return max;
        }
        else    // if level is odd
        {
            double min = 1;     // initialize min to highest value
            for(GameTreeNode child : node.getChildren())
            {
                double minTemp = nodeValue(child, level+1);
                if(minTemp < min)
                    min = minTemp;
            }
            return min;
        }
    }


    // requirements not met: was told that we need to create and then traverse
    // the tree. AlphaBeta does both simultaneously and much faster.
    // SRC: http://en.wikipedia.org/wiki/Alpha-beta_pruning

    public double alphaBeta(GameTreeNode node, double alpha, double beta, int player, int depth)
    {
        int gameOver = node.getBoard().checkWin();
        if(gameOver > 0)
        {
            if(gameOver == player)
                return 1;
            else
                return -1;
        }

        if(depth >= node.getBoard().getLookAhead())
        {
            return evaluate(node.getBoard(), player);
        }

        if(player == me)
        {
            //System.out.println("Called A: me = "+me);
            for(int x = 0; x < node.getBoard().getNumCols(); x++)
            {
                Board clone = clone(node.getBoard());
                if(clone.legalMove(x))
                {
                    clone.addMove(x,player);
                    alpha = Math.max(alpha, alphaBeta(new GameTreeNode(clone,x), alpha, beta, player%2 + 1, depth+1));
                    if(beta <= alpha)
                        return alpha;
                }
            }
            return alpha;
        }
        else    // player is not me
        {
            //System.out.println("Called B: me = "+me);
            for(int x = 0; x < node.getBoard().getNumCols(); x++)
            {
                Board clone = clone(node.getBoard());
                if(clone.legalMove(x))
                {
                    clone.addMove(x,player);
                    beta = Math.min(beta, alphaBeta(new GameTreeNode(clone,x), alpha, beta, player%2 + 1, depth+1));
                    if(beta <= alpha)
                        return beta;
                }
            }
            return beta;
        }
    }


    /**
     * Fills a given tree with subtrees of each move up to the given depth level.
     * @param node the root of the tree.
     * @param level the maximum depth to perform the recursion
     * @param playerNum The player number that the owns the next move.
     */
    public void populateTree(GameTreeNode node, int level, int playerNum)
    {
        Board board = node.getBoard();
        if(level <= (node.getBoard()).getLookAhead() && board.checkWin() < 0)
        {
            for(int x = 0; x < board.getNumCols(); x++)
            {
                Board clone = clone(node.getBoard());
                if(clone.legalMove(x))
                {
                    clone.addMove(x,playerNum);
                    node.addChild(new GameTreeNode(clone, x));
                }
            }
            for(GameTreeNode x : node.getChildren())
            {
                populateTree(x, level+1, playerNum%2 + 1);
            }
        }
        else   // if lowest level (all leafs)
        {
            node.setValue(evaluate(node.getBoard(), me));
            // value is the board, not the rating
            //node.setValue   (BoardEvaluation.evaluate(node.getValue(),true));
        }
    }

    /**
     * Creates a clone of the given board.
     * @param board the board to clone.
     * @return a copy of the board.
     */
    public Board clone(Board board)
    {
        Board returnBoard = new Board(board.getNumRows(),
                                        board.getNumCols(),
                                        board.getNumInARow(),
                                        board.getLookAhead());
        returnBoard.setBoard(board.getBoard());

        return returnBoard;
    }

}
