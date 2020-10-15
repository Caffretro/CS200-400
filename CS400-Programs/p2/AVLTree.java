/**
 * Filename:   AVLTree.java
 * Project:    p2
 * Authors:    Debra Deppeler, Junheng Wang
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    002
 * 
 * Due Date:   Before 10pm on September 24, 2018
 * Version:    1.0
 * 
 * Credits:    None
 * 
 * Bugs:       For now, no instruction on the case that trying to insert to a non-BST.
 */

import java.lang.IllegalArgumentException;

/**
 * This is an AVL tree that follows all the traits of a BST tree with balancing all the nodes
 * after each insert and delete action.
 * 
 * @param <K> tree nodes' keys are generic types that are comparable
 */
public class AVLTree<K extends Comparable<K>> implements AVLTreeADT<K> {
    private BSTNode<K> root;
    private int height = 0;

    public AVLTree() {
        this.root = null;
        height = 0;
    }

    /**
     * Represents a tree node. Each tree node contains fields that holds it key information, its
     * height information in the tree, and references to its left and right children. Constructor
     * will create a default node that the key information is given, and height is 1 since we treat
     * it as a leaf node, thus children are all null. Setter and Getter methods are also given
     * 
     * @param <K> each node's key is a generic type that is comparable
     */
    class BSTNode<K> {
        /* fields */
        private K key; // The information it brings
        private int height; // Its current height in the tree
        private BSTNode<K> left, right; // Reference to its children

        /**
         * Constructor for a BST node.
         * 
         * @param key
         */
        BSTNode(K key) {
            this.key = key;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setLeft(BSTNode<K> left) {
            this.left = left;
        }

        public void setRight(BSTNode<K> right) {
            this.right = right;
        }

        public K getKey() {
            return this.key;
        }

        public int getHeight() {
            return this.height;
        }

        public BSTNode<K> getLeft() {
            return this.left;

        }

        public BSTNode<K> getRight() {
            return this.right;

        }
    }

    /**
     * This method simply returns a boolean value about if the tree is empty or not
     * 
     * @return true if the tree is empty
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * This is a left rotate method and changes Parent to its right child's leftchild
     * 
     * @param p, a node that need to left rotate
     * @return k, a node that is now in p's original position
     */
    private BSTNode<K> leftRotate(BSTNode<K> p) {
        BSTNode<K> k = p.right;
        BSTNode<K> temp = k.left;

        k.left = p;
        p.right = temp;
        // Rotate done
        p.height = maxHeight(getNodeHeight(p.left), getNodeHeight(p.right)) + 1;
        k.height = maxHeight(getNodeHeight(k.left), getNodeHeight(k.right)) + 1;

        return k;
    }

    /**
     * This is a right rotate method and changes Parent to its left child's rightchild
     * 
     * @param p, a node that need to right rotate
     * @return k, a node that is now in p's original position
     */
    private BSTNode<K> rightRotate(BSTNode<K> p) {
        BSTNode<K> k = p.left;
        BSTNode<K> temp = k.right;

        k.right = p;
        p.left = temp;
        // Rotate done
        p.height = maxHeight(getNodeHeight(p.left), getNodeHeight(p.right)) + 1;
        k.height = maxHeight(getNodeHeight(k.left), getNodeHeight(k.right)) + 1;

        return k;
    }

    /**
     * This is a helper method that gets a node's height in a tree. Different from height getter, if
     * a node is null, its height is 0
     * 
     * @param node that need to check height
     * @return int value that represents its height in the tree
     */
    private int getNodeHeight(BSTNode<K> n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }

    /**
     * This is a simple comparison method that compares a node's children's heights, used to renew a
     * node's height by adding 1 to the maximum among these two heights.
     * 
     * @param leftHeight, a node's left child's height
     * @param rightHeight, a node's right child's height
     * @return int value that the bigger parameter will be passed back.
     */
    private int maxHeight(int leftHeight, int rightHeight) {
        if (leftHeight > rightHeight) {
            return leftHeight;
        } else {
            return rightHeight;
        }
    }

    /**
     * This method simply returns the difference of a node's subtrees' heights. If positive, then
     * left subtree is higher than right subtree. if a node is null, then it doesn't have any
     * children, thus balance factor is 0
     * 
     * @param node that wants to check balance
     * @return int value that represents the difference of subtrees' heights
     */
    private int getBalance(BSTNode<K> n) {
        if (n == null) {
            return 0;
        }
        return getNodeHeight(n.left) - getNodeHeight(n.right);
    }

    /**
     * This method is used to find a node's in-order successor in order to replace the node when we
     * are deleting this node.
     * 
     * @param node that is looking for its in-order successor
     * @return node that is n's in-order successor
     */
    private BSTNode<K> getMinValue(BSTNode<K> n) {
        BSTNode<K> temp = n;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    /**
     * This is basic function of a AVL tree that inserting a node into it. It calls a private helper
     * method to do the cases and recursion. Null values and duplicates cannot be inserted and will
     * throw exceptions. Always, if the tree is not a BST, we warn user and do nothing.
     * 
     * @param key that we want to insert
     */
    @Override
    public void insert(K key) throws DuplicateKeyException, IllegalArgumentException {
        if (!checkForBinarySearchTree()) {
            System.out.println("Warning: this tree is not a Binary Search Tree, "
                + "no insert operation will be done.");
            return;
        } else {
            try {
                if (key == null) {
                    throw new IllegalArgumentException();
                }
                root = insertHelper(key, this.root);
                // Refreshing the height info for root node.
                height = root.height;
            } catch (ClassCastException e) {
                throw new IllegalArgumentException();
                // This is for handling
            }
            // Duplicate Exception is going to be caught by test methods
        }
    }

    /**
     * Private helper for insert(). it first goes down to the place by comparing values of nodes,
     * then create a new node there, renewing height information and rebalancing each level it might
     * influenced while recursively going upwards. each recursion returns the current subtree's root
     * to keep track of what we have done in lower levels. In the last recursion that ends helper,
     * the renewed tree's root will be passed back. recursively going back
     * 
     * @param key that we want to insert
     * @param n each recursion's subtree's root
     * @return each recursion's subtree's new root
     * @throws DuplicateKeyException
     */
    private BSTNode<K> insertHelper(K key, BSTNode<K> n) throws DuplicateKeyException {
        // Step I: finding where to insert our guy.
        if (n == null) {
            return new BSTNode<K>(key);
        } else {
            if (key.compareTo(n.getKey()) < 0) {
                n.setLeft(insertHelper(key, n.getLeft()));
            } else if (key.compareTo(n.getKey()) > 0) {
                n.setRight(insertHelper(key, n.getRight()));
            } else {
                throw new DuplicateKeyException();
            }
        }
        // Step II: From now on, I can track the height by adding 1 to each node, then do rotate.

        n.height = 1 + maxHeight(getNodeHeight(n.left), getNodeHeight(n.right));
        // Step III: Rebalancing the tree. Four kinds of rotate we have.
        int balanceFactor = getBalance(n);
        // if bigger than 0, left tree is heavier.

        if (balanceFactor > 1 && key.compareTo(n.left.key) < 0) { // Right once
            return rightRotate(n);
        }
        if (balanceFactor < -1 && key.compareTo(n.right.key) > 0) { // Left once
            return leftRotate(n);
        }
        if (balanceFactor > 1 && key.compareTo(n.left.key) > 0) { // Left-Right rotate
            n.left = leftRotate(n.left);
            return rightRotate(n);
        }
        if (balanceFactor < -1 && key.compareTo(n.right.key) < 0) { // Right-Left rotate
            n.right = rightRotate(n.right);
            return leftRotate(n);
        }
        return n;
        // That's for not losing track
    }

    /**
     * Delete is another basic function of the tree. Algorithms are highly similar to insert. But
     * there are still some differences. First, trying to delete a key that does not exist in the
     * tree is acceptable, and no change will be made. Second, trying to delete a key that is null
     * will throw an IllegalArgument Exception.
     * 
     * @param key, a key that we want it to disappear from the tree.
     */
    @Override
    public void delete(K key) throws IllegalArgumentException {
        if (!checkForBinarySearchTree()) {
            System.out.println(
                "Warning: this tree is not a Binary Search Tree, no delete operation will be done.");
            return;
        } else {
            if (key == null) {
                throw new IllegalArgumentException();
            }
            try {
                root = deleteHelper(key, this.root);
                // Refreshing the height info for root node.
                if (root == null) {
                    height = 0;
                } else {
                    height = root.height;
                }
            } catch (NullPointerException e) {
                System.out.println(
                    "Unexpectedly threw NullPointer Exception, delete operation might went wrong");
            }
            return;
        }

    }

    /**
     * Helper recursive method for delete(). Basic algorithm is all the same. Difference is that
     * some particular cases where root of tree is deleted, a special case will be caught and
     * treated differently. We always find where to delete that node, then first we delete it,
     * renewing every recursion's node's height, then check if we need to rebalance the tree.
     * 
     * @param key, the key we want to delete from the tree
     * @param n, BSTNode that keep track of where we are in the tree
     * @return n, BSTNode that keep track of where we are going backwards in the tree
     */
    private BSTNode<K> deleteHelper(K key, BSTNode<K> n) {
        if (n == null) {
            return n;
        }
        // Part I: Finding that guy
        if (key.compareTo(n.key) < 0) {
            n.left = deleteHelper(key, n.left);
        } else if (key.compareTo(n.key) > 0) {
            n.right = deleteHelper(key, n.right);
        } // Cases where the node is not what we wanted.
        else { // Where we found the node we want to delete
               // Cases where the node we want to delete has one or no children
            if (n.left == null || n.right == null) {
                BSTNode<K> temp = null;
                if (temp == n.left) {
                    temp = n.right;
                } else {
                    temp = n.left;
                }
                // If it has one children, then temp is that children; if no children, temp is null

                // No children case
                if (temp == null) {
                    temp = n;
                    n = null;
                }
                // One children case: replace node with children
                else {
                    n = temp;
                }
            } else {
                // Cases where the node we want to delete has 2 children
                // Choose to replace the value with in-order successor.
                BSTNode<K> replace = getMinValue(n.getRight());
                n.key = replace.key;
                n.right = deleteHelper(replace.key, n.right);
                // After replacing node's information with in-order successor, we delete that
                // successor using another recursion.
            }

            if (n == null) {
                return n;
            }

            // Be aware that if there is only one node in the tree and we delete it, no more
            // balancing is necessary
        }
        // Part II: Updating height.
        n.height = 1 + maxHeight(getNodeHeight(n.left), getNodeHeight(n.right));

        // Part III: Rebalancing tree
        int balanceFactor = getBalance(n);
        // Again, if balanceFactor bigger than 1, left subtree is heavier
        // LeftRotate once
        if (balanceFactor < -1 && getBalance(n.right) <= 0) {
            return leftRotate(n);
        }
        // RightRotate once
        if (balanceFactor > 1 && getBalance(n.left) >= 0) {
            return rightRotate(n);
        }
        // Right-Left case
        if (balanceFactor < -1 && getBalance(n.right) > 0) {
            n.right = rightRotate(n.right);
            return leftRotate(n);
        }
        // Left-Right case
        if (balanceFactor > 1 && getBalance(n.left) < 0) {
            n.left = leftRotate(n.left);
            return rightRotate(n);
        }
        return n;
    }

    /**
     * This method searches through the tree to see if the key we are looking for is in one of the
     * tree's nodes. Searching a null key is illegal and will throw a IllegalArgument Exception.
     * If the tree is empty, false will be returned since there's nothing in the tree. If no 
     * correspondence can be found, helper method will throw a NullPointer Exception and will be 
     * caught, where a false value will be returned and a warning message will be printed.
     * @param key, the key we want to check if it is in the tree
     * @return boolean value of if the key can be found
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    @Override
    public boolean search(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Warning: Searching null is not proper");
        }
        try {
            if (isEmpty()) {
                return false;
            }
            return searchHelper(key, root);
        } catch (NullPointerException e) {
            // System.out.println("Warning: Lightning Storm created."), just kidding.
            System.out.println("Warning: the value " + key + " you are searching is not in the tree");
            // cases where no correspondence can be found
            return false;
        }
    }
    /**
     * This helper method compares key with nodes' values from root and recursively going downwards.
     * If a node contains same value as key, we return true, otherwise the recursion will never
     * stop until it trys to call a leaf node's null children's getKey() method, which indicates 
     * no correspondence can be found and a NullPointer Exception will be passed back
     * @param key, the key we want to search
     * @param n, a BSTNode that keeps track of where we are in the tree
     * @return boolean value of if we can find that value
     * @throws NullPointerException
     */
    private boolean searchHelper(K key, BSTNode<K> n) throws NullPointerException {
        if (key.compareTo(n.getKey()) > 0) {
            return searchHelper(key, n.right);
        } else if (key.compareTo(n.getKey()) < 0) {
            return searchHelper(key, n.left);
        } else {
            return true;
        }
    }

    /**
     * This method will return a String that has every node's key value followed by a space through
     * in-order traversal, which is from lowest value to highest value by calling a recursive 
     * helper method.
     * @return a String that contains all node's key information
     */
    @Override
    public String print() {
        return printHelper(root);
    }
    /**
     * Recursively adds nodes key values by in-order traversal
     * @param n, BSTNode to keep track of where we are in the tree
     * @return a string that contains nodes key value information followed by a space
     */
    private String printHelper(BSTNode<K> n) {
        String str = "";
        if (n == null) // will be true iff root is null
            return str;
        if (n.getLeft() != null)
            str += printHelper(n.getLeft());
        str += n.getKey() + " ";
        if (n.getRight() != null)
            str += printHelper(n.getRight());
        return str;
    }

    /**
     * This is a method used to check if the tree is balanced by calling a recursive private helper
     * method.
     * @return boolean value of if the tree is balanced
     */
    @Override
    public boolean checkForBalancedTree() {
        if (isEmpty()) {
            return true;
        }
        return checkForBalancedHelper(root);

    }
    /**
     * This helper method recursively checks if the balance factor of each node in the tree is in
     * range -1 to 1. If out of range, the tree is not balanced.
     * @param n, BSTNode to keep track of where we are in the tree
     * @return true if the subtree is balanced
     */
    private boolean checkForBalancedHelper(BSTNode<K> n) {
        boolean balanced = false;
        if (n != null) {
            if (getBalance(n) <= 1 && getBalance(n) >= -1) {
                balanced = true;
            }
        } else {
            return true;
        }
        return checkForBalancedHelper(n.getLeft()) && balanced
            && checkForBalancedHelper(n.getRight());
    }

    /**
     * This is a method that checks if the trees nodes follow the rule of BST by calling a private
     * helper method to recursively check if every node follows the rule.
     * @return true if the tree is a BST
     */
    @Override
    public boolean checkForBinarySearchTree() {
        if (isEmpty()) {
            return true;
        }
        BSTNode<K> previous = null;
        return checkForBSTHelper(previous, root);
    }
    /**
     * By recursively going through every node using in-order traversal, we compare each node with 
     * its previous node to see if all the nodes follow the rule
     * @param prev, BSTNode that keeps track of node's previous
     * @param n, BSTNode that keeps track of where we are in the tree
     * @return true if each node is smaller than its previous node.
     */
    private boolean checkForBSTHelper(BSTNode<K> prev, BSTNode<K> n) {
        if (n != null) {
            if (!checkForBSTHelper(prev, n.getLeft())) {
                return false;
            }

            if (prev != null && n.getKey().compareTo(prev.getKey()) <= 0) {
                return false;
            }
            prev = n;
            // We assign n's value to prev, and goes next recursion
            return checkForBSTHelper(prev, n.getRight());
        }
        return true;
    }
}
