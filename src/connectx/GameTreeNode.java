
package connectx;

import java.util.ArrayList;

/**
 *
 * @author C13Zachary.Zeitlin
 */
public class GameTreeNode
{
    private int lastMove;           // the last move made to reach this position
    private double boardValue;                 // Rating of the board -1 = loss, 1 = win
    private Board board;                // Generic object to store in the node
    private ArrayList<GameTreeNode> children; // The static list of linked nodes

    /**
     * Constructor for the GameTreeNode which sets a generic value and maximum
     * number of nodes to link to.
     * @param value The object to store within this node.
     * @param previousMove The column of the previous move that reached the
     * current board position.
     */
    public GameTreeNode(Board value, int previousMove)
    {
        lastMove = previousMove;
        board = value;
        children = new ArrayList<GameTreeNode>();
    }

    /**
     * Sets the evaluation value of the board held by the node.
     * @param val the value of the board.
     */
    public void setValue(double val)
    {
        boardValue = val;
    }

    /**
     * Gets the particular child at the given element number.
     * @param x the nth child to get.
     * @return the child-node of this current node.
     */
    public GameTreeNode getChild(int x)
    {
            return children.get(x);
    }

    /**
     * Gets the last move that was made in order to reach the current position.
     * @return the column number that represents the last move made.
     */
    public int getLastMove()
    {
        return lastMove;
    }

    /**
     * Checks if the node is a leaf (if it has no children).
     * @return true if there are no children, false otherwise.
     */
    public boolean isLeaf()
    {
        return children.isEmpty();
    }

    /**
     * Gets the evaluation value of the board held by the current node.
     * @return the value of the board evaluation.
     */
    public double getValue()
    {
        return boardValue;
    }
    /**
     * Gets the generic value stored by this node.
     * @return The generic value stored by this node.
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Gets a list of children held by this node. Note: potential null pointers
     * when a move is not valid, it is represented as null in the array.
     * @return a static array of generic types representing all children.
     */
    public ArrayList<GameTreeNode> getChildren()
    {
        return children;
    }
    

    /**
     * Adds a child-node (if maximum number of children is not met yet) to the
     * static array of children.
     * @param node The node to add.
     */
    public void addChild(GameTreeNode node)
    {
            children.add(node);
    }
    
}
