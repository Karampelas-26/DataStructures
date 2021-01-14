public class TreeNode {

    private Suspect item;
    private TreeNode left; //pointer to left subtree
    private TreeNode right; //pointer to right subtree
    private TreeNode parent;
    private int N; //number of subtree rooted at this TreeNode

    public TreeNode(Suspect item) {
        this.item = item;
    }

    public TreeNode() {
    }

    public Suspect getItem() {
        return item;
    }

    public void setItem(Suspect item) {
        this.item = item;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getN() {
        return N;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
