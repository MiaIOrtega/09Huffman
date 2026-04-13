
/**
 * The blueprint of a Huffman Coding Tree.
 */
public class HuffmanTree {
    
    private TreeNode root;
    
    /**
     * Creates a new HuffmanTree.
     */
    public HuffmanTree() {
        this.root = null;
    }
    
    /**
     * Adds the left and right node from the queue to
     * the root node.
     * 
     * @param leftNode The node with higher priority (or
     * lesser value).
     * @param rightNode The node with lower priority (or
     * greater value).
     */
    public void add(TreeNode leftNode, TreeNode rightNode) {
        // checks preconditions
        if (leftNode == null || rightNode == null) {
            throw new IllegalArgumentException("Violation of precondition: add."
                    + " leftNode and rightNode must not be null.");
        }
        
        root = new TreeNode(leftNode, 0, rightNode);
    }
    
//    public void addLeft() {}      // idk if i'll need this later
//    
//    public void addRight() {}
    
    /**
     * Returns the root node.
     * 
     * @return The root node of this
     * Huffman Coding Tree is returned.
     */
    public TreeNode getRootNode() {
        return root;
    }
    
    /**
     * Prints the Huffman Coding Tree.
     */
    public void printTree() {
        printTree(root, "");
    }
    
    /**
     * Helper Method to help print the 
     * Huffman Coding Tree.
     * 
     * @param node The current node to print at.
     * @param spaces The amount of spaces apart the nodes are.
     */
    private void printTree(TreeNode node, String spaces) {
        if (node != null) {
            printTree(node.getRight(), spaces + "  ");
            System.out.println(spaces + node.getValue());
            printTree(node.getLeft(), spaces + "  ");
        }
    }
}
