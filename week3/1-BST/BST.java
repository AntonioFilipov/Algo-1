package week3;

public class BST {

    public static class Node {

        public int value;
        public Node left;
        public Node right;
    }

    // Checks if a binary tree is a binary search tree.
    public boolean isBST(Node root) {
        return BSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean BSTUtil(Node root, int minValue, int maxValue) {

        if (root == null) {
            return true;
        }

        if (checkLeftSubTree(root.left, minValue, root.value) && checkRightSubTree(root.right, root.value, maxValue)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkLeftSubTree(Node node, int minValue, int maxValue) {
        if (node == null) {
            return true;
        }

        if (node.value < minValue || node.value > maxValue) {
            return false;
        }

        return checkLeftSubTree(node.left, minValue, node.value) && checkRightSubTree(node.right, node.value, maxValue);
    }

    private boolean checkRightSubTree(Node node, int minValue, int maxValue) {
        if (node == null) {
            return true;
        }

        if (node.value <= minValue || node.value > maxValue) {
            return false;
        }

        return checkLeftSubTree(node.left, minValue, node.value) && checkRightSubTree(node.right, node.value, maxValue);
    }
}