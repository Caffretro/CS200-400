//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P10 Simple Search ENgine
// Files:           None
// Course:          CS300 Spring 2018
//
// Author:          Junheng Wang
// Email:           jwang922@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Lecturer's Name: N/A
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a binary search tree implementation in which each node in the tree is a WebPageNode
 * class. We use this class to do operations on a database of webPageNodes, either add item to it,
 * counting the existing number of webpages in the tree, search for a webpage, or print them all out
 * to the console.
 * 
 * @author Junheng Wang
 */
public class SearchEngine {

    private WebPageNode root; // root of the BST-based search engine

    /**
     * This is a no-arg constructor which sets the root field to null
     */
    public SearchEngine() {
        this.root = null;
    } // creates an empty search engine

    /**
     * Checks if the tree is currently empty
     * 
     * @return boolean value of if the tree is empty
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Inserts a node to the tree, be aware of adding duplicate id or illegal instructions of
     * calling this method
     * 
     * @param String that is the id of the node
     * @param String that stores the webLink
     * @throws IllegalArgumentException
     */
    public void insert(String id, String webLink) throws IllegalArgumentException{
        if (isEmpty()) {
            this.root = new WebPageNode(id, webLink);
        } else {
            findPlaceToInsert(root, id, webLink);
        }
    }

    /**
     * Helper method to iterate through the tree and find a place for the node to insert, by
     * comparing id of nodes in the tree
     * 
     * @param current node that we are looking
     * @param id of the node we want to insert
     * @param webLink of the node that we want to insert
     * @throws IllegalArgumentException
     */
    private void findPlaceToInsert(WebPageNode current, String id, String webLink)
        throws IllegalArgumentException {
        if (id.equals(current.getId())) {
            throw new IllegalArgumentException();
        }
        // since duplicate ids are not allowed
        if (id.compareTo(current.getId()) < 0) {
            if (current.getLeftChild() != null) {
                findPlaceToInsert(current.getLeftChild(), id, webLink);
            } else {
                current.setLeftChild(new WebPageNode(id, webLink));
            }
            // keep going to the left until we cannot find a smaller one
        } else if (id.compareTo(current.getId()) > 0) {
            if (current.getRightChild() != null) {
                findPlaceToInsert(current.getRightChild(), id, webLink);
            } else {
                current.setRightChild(new WebPageNode(id, webLink));
            }
            // keep going to the right until we cannot find a smaller one
        }

    }

    /**
     * Search the tree for the node that has the id, if not found, return a message that did not
     * found
     * 
     * @param String that stores the id we want to search
     * @return String that holds the webLink of that id
     */
    public String searchWebPage(String id) {
        String s = findWebPage(root, id);
        if (s != null) {
            return s;
        } else {
            return "No web Link found for the web page " + id + ".";
        }
    }

    // A look-up method that
    // searches for a webPageNode with the given id in the current
    // search engine and returns the related weblink if that webPageNode
    // is present. Otherwise, a Warning message starting with
    // "No web link found" should be returned back, for instance:
    // "No web link found for the web page <id>".
    // !!!! A helper method should be used here !!!!
    // The searchWebPage method may call a private recursive
    // helper method to operate
    /**
     * Private helper method that goes through the tree to compare the id, if found, return the
     * weblink, otherwise return null
     * 
     * @param current node that we are looking
     * @param id of the node we want to search
     * @return String webPage of the node or null if not found
     */
    private String findWebPage(WebPageNode current, String id) {
        String s = "";
        if (id.equals(current.getId())) {
            return current.getWebLink();
        }
        // base case where found
        if (id.compareTo(current.getId()) < 0) {
            if (current.getLeftChild() != null) {
                s = findWebPage(current.getLeftChild(), id);
            } else {
                return null;
            }
            // whether the node is still in front or not found
        } else if (id.compareTo(current.getId()) > 0) {
            if (current.getRightChild() != null) {
                s = findWebPage(current.getRightChild(), id);
            } else {
                return null;
            }
            // whether the node is still in back or not found
        }
        return s;
    }

    /**
     * This method uses the getAllWebPages() method's helper method to count how many list numbers
     * are in the ArrayList, in other words, the number of nodes in the tree.
     * 
     * @return number of valid nodes in the tree
     */
    public int getWebPageCount() {
        ArrayList<String> list = new ArrayList<String>();
        addItem(root, list);
        return list.size();
    }

    // returns the number of webPageNodes
    // stored in the search engine
    // !!!! A helper method should be used here !!!!
    // The getWebPageCount method should call a private recursive
    // helper method to operate
    /**
     * Goes through the tree and assemble them in an ascending sequence by their ids. Using a
     * private helper method to add them to the arrayList
     * 
     * @return ArrayList of String elements
     */
    public ArrayList<String> getAllWebPages() {
        ArrayList<String> list = new ArrayList<String>();
        addItem(root, list);
        return list;
    }

    /**
     * The method to count and to print out the list. Goes through the list and sorts the nodes in
     * ascending order
     * 
     * @param current node we are looking
     * @param ArrayList that holds the value of tree members
     */
    private void addItem(WebPageNode current, ArrayList<String> list) {
        if (current == null) {
            return;
        }
        // base case
        if (current.getLeftChild() != null) {
            addItem(current.getLeftChild(), list);
        }
        list.add(current.getId());
        if (current.getRightChild() != null) {
            addItem(current.getRightChild(), list);
        }
        // Add in ascending order
    }

    /**
     * Additional private helper method that prints all the ArrayList members
     * 
     * @param ArrayList of String
     */
    private void printList(ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("Search Engine is empty.");
        } else {
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + ", ");
            }
            System.out.println(list.get(list.size() - 1));
        }
    }
    // returns an ArrayList
    // of String that stores all the id fields of the webPageNodes
    // present in the current search engine, sorted in alphabetical
    // order from left to right.
    // !!!! A helper method should be used here !!!!
    // The getAllWebPages method may call a private recursive
    // helper method to operate

    // You can add additional private helper methods to help organize
    // your implementation for these functions. You can add also
    // a main method. But, no additional fields or public methods
    // should be added to this class.
    /**
     * Main method that creates a new SearchEngine instance and do operations on the search engine
     * 
     * @param args
     */
    public static void main(String[] args) {
        /*
         * SearchEngine test = new SearchEngine(); test.insert("GalileoGalilei",
         * "https://www.famousscientists.org/galileo-galilei"); test.insert("FrancisGalton",
         * "https://www.famousscientists.org/francis-galton"); test.insert("JWillardGibbs",
         * "https://www.famousscientists.org/j-willard-gibbs"); test.insert("rosaFranklin",
         * "https://en.wikipedia.org/wiki/Rosalind_Franklin"); test.insert("JohnHerschel",
         * "https://www.famousscientists.org/john-herschel");
         * System.out.println(test.searchWebPage("GalileoGalilei"));
         * System.out.println(test.searchWebPage("FrancisGalton"));
         * System.out.println(test.searchWebPage("JohnHerschel"));
         * System.out.println(test.getWebPageCount()); ArrayList<String> testList =
         * test.getAllWebPages(); for (int i = 0; i < testList.size(); i++) {
         * System.out.println(testList.get(i)); }
         */
        Scanner scnr = new Scanner(System.in);
        String command = "";
        SearchEngine search = new SearchEngine();
        String[] info = null;
        do {
            System.out.println();
            System.out
                .println("=========================== Search Engine ============================");
            System.out
                .println("1. Enter 'i <id> <webLink>' to insert a web page in the search engine");
            System.out.println("2. Enter 's <id>' to search a web link in the search engine");
            System.out.println("3. Enter 'p' to print all the web page ids in the search engine");
            System.out
                .println("4. Enter 'c' to get the count of all web pages in the search engine");
            System.out.println("5. Enter 'q' to quit the program");
            System.out
                .println("======================================================================");
            System.out.println();
            System.out.print("Please enter your command:");
            command = scnr.nextLine();
            // scan action
            switch (command.charAt(0)) {
                // insert
                case 'i':
                case 'I':// insert()
                    info = command.split(" ");
                    if (info.length != 3) {
                        if (info.length < 3) {
                            System.out.println("WARNING: failed to insert web page: "
                                             + "Id/web link can't be blank!");
                        } else {
                            System.out.println("WARNING: Unrecognized command.");
                        }
                    } else {
                        try {
                            search.insert(info[1], info[2]);
                        } catch(IllegalArgumentException e) {
                            System.out.println("WARNING: failed to insert duplicate web page: " + info[1]
                                               + ".");
                        }
                    }
                    break;
                // search
                case 's':
                case 'S':// search()
                    info = command.split(" ");
                    if (info.length != 2) {
                        if ((info[0].equals("s") || info[0].equals("S")) && info.length < 2) {
                            System.out.println("WARNING: Invalid syntax for search operation: "
                                             + "Id link can't be blank!");
                        }
                        else {
                            System.out.println("WARNING: Unrecognized command.");
                        }
                    }
                    else {
                        try {
                            System.out.println(info[1] + " - " + search.searchWebPage(info[1]));
                        } catch(NullPointerException e) {
                            System.out.println(info[1] + " - No web link found for the web page "
                                             + info[1] + ".");
                        }
                    }
                    break;
                // prints the tree
                case 'p':
                case 'P'://print the list
                    if (command.equals("p") || command.equals("P")) {
                        ArrayList<String> list = search.getAllWebPages();
                        search.printList(list);
                    }
                    else {
                        System.out.println("WARNING: Unrecognized command.");
                    }
                    break;
                // counts the number of tree nodes
                case 'c':
                case 'C':// count numbers
                    if (command.equals("c") || command.equals("C")) {
                        System.out.println(search.getWebPageCount());
                    }
                    else {
                        System.out.println("WARNING: Unrecognized command.");
                    }
                    break;
                // escape
                case 'q':
                case 'Q':// quit
                    if (command.equals("q") || command.equals("Q")) {
                        
                    }
                    else {
                        System.out.println("WARNING: Unrecognized command.");
                    }
                    break;
                // when the instruction is unclear
                default:
                    System.out.println("WARNING: Unrecognized command.");
                    break;
            }
        } while (!command.equals("q") && !command.equals("Q"));
        scnr.close();
    }
}
