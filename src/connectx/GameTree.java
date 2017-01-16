


package connectx;

/**
 *
 * @author C13Zachary.Zeitlin
 */

public class GameTree
{
    private GameTreeNode root;  // pointer to root node of tree

    /**
     * Constructor that creates a root node with a max number of children
     * @param value The value of the root node.
     */
    public GameTree(Board value)
    {
        root = new GameTreeNode(value, 0);
    }

    /**
     * Gets the root node of the tree.
     * @return the root node of the tree.
     */
    public GameTreeNode getRoot()
    {
        return root;
    }

    /*
    public final void populateTree(GameTreeNode node, int level)
    {
        if(level < maxLevels)
        {
            for(int x = 0; x < gameBoard.getNumCols(); x++)
            {
                Board clone = BoardEvaluation.clone(gameBoard);
                if(clone.legalMove(x))
                    clone.addMove(x,level%2+1);
                node.addChild(new GameTreeNode(clone));
            }
            for(GameTreeNode x : node.getChildren())
            {
                populateTree(x, level+1);
            }
        }
        else    // if lowest level (all leafs)
        {
            node.setValue(BoardEvaluation.evaluate(node.getBoard()));
        }
    }
*/



    
    // 2 levels deep level-order printout
    @Override
    public String toString()
    {
        String ret = "ROOT\n";
        for(GameTreeNode child : this.getRoot().getChildren())
            ret += (child.getValue() + ", ");
        ret += "\n";
        for(GameTreeNode child : this.getRoot().getChildren())
            for(GameTreeNode subChild : child.getChildren())
                ret += (subChild.getValue() + ", ");

        return ret;
    }
}
