/**
 * Filename:   TestAVLTree.java
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
 * Bugs:       no known bugs, but not complete either
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** 
 * This is a test class for AVLTree classes that has implemented AVLTreeADT, which used JUnit4 as a
 * tool. Major functions and marginal cases are considered in this test. If any test fails, a red 
 * bar will appear to warn the user. If no test fails, the bar will be green.
 */
public class TestAVLTree {
    private AVLTree<Integer> tree = new AVLTree<Integer>();
    // Since we are testing inserting a huge amount of numbers into a tree, a timeout is not
    // necessary.

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {
        tree = new AVLTree<Integer>();
        // For every test, a new AVLTree will be provided.
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
        // And after every test, clear that tree for further use.
    }

    /**
     * Tests that an AVLTree is empty upon initialization.
     */

    @Test
    public void test01isEmpty() {
        assertTrue(tree.isEmpty());
    }

    /**
     * Tests that an AVLTree is not empty after adding a node.
     */
    @Test
    public void test02isNotEmpty() {
        try {
            tree.insert(1);
            assertFalse(
                "test02isNotEmpty fails: inserted 1 to " + "AVLTree but isEmpty returned true.",
                tree.isEmpty());
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests functionality of a single delete following several inserts.
     */
    @Test
    public void test03insertManyDeleteOne() {
        boolean illegalCaught = false;
        try {
            tree.insert(1);
            tree.insert(8);
            tree.insert(27);
            tree.insert(87);
            tree.insert(59);
            tree.delete(8);
            assertFalse(
                "test03insertManyDeleteOne fails: deleted 8 from " + "AVLTree but unable to do so.",
                tree.search(8));
            assertTrue(
                "test03insertManyDeleteOne fails: tree is not " + "balanced after deleting 8.",
                tree.checkForBalancedTree());
            assertTrue(
                "test03insertManyDeleteOne fails: tree is no longer " + "a BST after deleting 8.",
                tree.checkForBalancedTree());
            
            tree.delete(null);
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            illegalCaught = true;
        }
        assertTrue("test03insertManyDeleteOne fails: deleting null should throw "
            + "IllegalArgumentException but didn't.", illegalCaught);
    }

    /**
     * Tests functionality of many deletes following several inserts.
     */
    @Test
    public void test04insertManyDeleteMany() {
        try {
            tree.insert(2);
            tree.insert(9);
            tree.insert(17);
            tree.insert(69);

            tree.delete(9);
            assertTrue(
                "test04insertManyDeleteMany fails: tree is not " + "balanced after deleting 9.",
                tree.checkForBalancedTree());
            assertTrue(
                "test04insertManyDeleteMany fails: tree is no longer " + "a BST after deleting 9.",
                tree.checkForBinarySearchTree());
            tree.delete(17);
            assertTrue(
                "test04insertManyDeleteMany fails: tree is not " + "balanced after deleting 17.",
                tree.checkForBalancedTree());
            assertTrue(
                "test04insertManyDeleteMany fails: tree is no longer " + "a BST after deleting 17.",
                tree.checkForBinarySearchTree());
            tree.delete(2);
            assertTrue(
                "test04insertManyDeleteMany fails: tree is not " + "balanced after deleting 2.",
                tree.checkForBalancedTree());
            assertTrue(
                "test04insertManyDeleteMany fails: tree is no longer " + "a BST after deleting 2.",
                tree.checkForBinarySearchTree());
            tree.delete(69);
            assertTrue(
                "test04insertManyDeleteMany fails: tree is not " + "balanced after deleting 69.",
                tree.checkForBalancedTree());
            assertTrue(
                "test04insertManyDeleteMany fails: tree is no longer " + "a BST after deleting 69.",
                tree.checkForBinarySearchTree());
            // Deleting all nodes from tree to make it empty. Lastly, check if we emptied the tree
            assertTrue("test04insertManyDeleteMany fails: inserted values should be "
                + "cleared after deleting all inserted values: 2, 9, 17, 69.", tree.isEmpty());
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Test that if the search method's every function works.
     */
    @Test
    public void test05testSearch() {
        boolean testNull = false;
        try {
            tree.insert(3);
            tree.insert(45);
            tree.insert(97);

            assertTrue(
                "test05testSearch fails: inserted value 97 but " + "searching 97 returned false.",
                tree.search(97));
            assertFalse("test05testSearch fails: searching a value 50 that is "
                + "not in the tree returned true.", tree.search(50));
            tree.delete(3);
            tree.delete(45);
            tree.delete(97);
            assertFalse("test05testSearch fails: searching a value 50 in an "
                + "empty tree didn't return false.", tree.search(50));
            // This line is to test whether searching something in an empty tree returns false.
            tree.search(null);
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            testNull = true;
        }
        assertTrue("test05testSearch fails: searching null should throw "
            + "IllegalArgument Exception but didn't.", testNull);

    }
    
    /**
     * Test if inserting a few items will maintain the rule of BST tree, which is sort them in 
     * ascending order from left child, root, and right child.
     */
    @Test
    public void test06isAscendingOrder() {
        try {
            for (int i = 1; i < 50; i++) {
                tree.insert(i);
                tree.insert(100 - i);
            }
            assertTrue("test06isAscendingOrder fails: inserting 100 different values "
                + "ends up with a non-BST tree.", tree.checkForBinarySearchTree());
            // If the tree is BST, then nodes inside is in ascending order.
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Test that if the print method correctly returns a string which includes nodes' key followed
     * by a space
     */
    @Test
    public void test07testPrint() {
        String[] compare = {"3", "8", "25", "76", "97"};
        try {
            tree.insert(3);
            tree.insert(8);
            tree.insert(97);
            tree.insert(76);
            tree.insert(25);
            String[] tester = tree.print().split(" ");
            for (int i = 0; i < 5; i++) {
                assertTrue(
                    "test07testPrint fails: print should return value " + compare[i] + " at No." + i
                        + " in returned values but " + "returned " + tester[i],
                    compare[i].compareTo(tester[i]) == 0);
            }
            // Since ADT required output to be key followed by space, we split by space and compare.
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test that if insert will throw DuplicateKeyException correctly.
     */
    @Test
    public void test08testDuplicateKeyException() {
        boolean caughtDuplicate = false;
        try {
            tree.insert(5);
            tree.insert(5);

        } catch (DuplicateKeyException e) {
            caughtDuplicate = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        assertTrue("test08testDuplicateKeyException fails: inserting two identical "
            + "values should throw DuplicateKey Exception but didn't.", caughtDuplicate);
    }
    
    /**
     * Test that if insert will throw IllegalArgumentException correctly.
     */
    @Test
    public void test09testInsertIllegalArgumentException() {
        boolean caughtIllegal = false;
        try {
            tree.insert(null);
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            caughtIllegal = true;
        }
        assertTrue("test09testInsertIllegalArgumentException fails: inserting a null should "
            + "throw IllegalArgument Exception but didn't", caughtIllegal);
    }
    /**
     * Test that if inserting 10000 items results in a correct balanced BST tree.
     */
    @Test
    public void test10testInsertLargeAmount() {
        try {
            for (int i = 1; i < 5000; i++) {
                tree.insert(i);
                tree.insert(10000 - i);
                assertTrue(
                    "test10testInsertLargeAmount fails: tree is not "
                        + "balanced after tree has reached its maximum capacity " + 2 * i + ".",
                    tree.checkForBalancedTree());
                assertTrue(
                    "test10testInsertLargeAmount fails: tree is no longer "
                        + "a BST after tree has reached its maximum capacity " + 2 * i + ".",
                    tree.checkForBinarySearchTree());
                assertTrue(
                    "test10testInsertLargeAmount fails: can't find value " + i
                        + " after tree has reached its maximum capacity " + 2 * i + ".",
                    tree.search(i));
                assertTrue(
                    "test10testInsertLargeAmount fails: can't find value " + (10000 - i)
                        + " after tree has reached its maximum capacity " + 2 * i + ".",
                    tree.search(10000 - i));

            }
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test that if insert and delete combined will function correctly, specifically after deleting
     * several items and added again.
     */
    @Test
    public void test11InsertDeleteCombined() {
        try {
            tree.insert(7);
            tree.insert(90);
            tree.insert(68);
            tree.insert(49);
            tree.insert(2);
            tree.delete(7);
            tree.delete(49);
            tree.delete(2);

            tree.insert(77);
            tree.insert(102);
            assertTrue("test11InsertDeleteCombined fails: adding 77 after insert and delete "
                + "combined can't be searched.", tree.search(77));
            assertTrue("test11InsertDeleteCombined fails: adding 102 after insert and delete "
                + "combined can't be searched.", tree.search(102));
            assertTrue("test11InsertDeleteCombined fails: tree is no longer balanced "
                + "after combining delete and insert.", tree.checkForBalancedTree());
            assertTrue("test11InsertDeleteCombined fails: tree is no longer a BST "
                + "after combining delete and insert.", tree.checkForBinarySearchTree());
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
