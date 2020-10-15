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

/**
 * This is a class which contains the information of each node inside the binary search tree engine
 * SearchEngine we implemented, it has the reference to its children, and the setter and getter
 * methods of its fields that will be used by SearchEngine
 * 
 * @author Junheng Wang
 */
public class WebPageNode {

    private final String id; // The id of the web page
    private final String webLink; // The web link of the web page
    private WebPageNode leftChild; // The leftChild of the the current WebPageNode
    private WebPageNode rightChild; // The rightChild of the the current WebPageNode


    /**
     * Each node's default constructor is just setting the two final fields
     * 
     * @param the id of webLink
     * @param the String webLink
     */
    public WebPageNode(String id, String webLink) {
        this.id = id;
        this.webLink = webLink;
    }
    // This should be
    // the only constructor for this class

    /**
     * Returns a WebPageNode's left child
     * 
     * @return WebPageNode that is the current's left child
     */
    public WebPageNode getLeftChild() {
        return this.leftChild;
    }

    /**
     * Sets the left child of the current child
     * 
     * @param A WebPageNode leftChild
     */
    public void setLeftChild(WebPageNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Returns a WebPageNode's right child
     * 
     * @return WebPageNode that is the current's right child
     */
    public WebPageNode getRightChild() {
        return this.rightChild;
    }

    /**
     * Sets the right child of the current child
     * 
     * @param A WebPageNode RightChild
     */
    public void setRightChild(WebPageNode rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Gets a WebPageNode's id
     * 
     * @return String id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets a WebPageNode's webLink
     * 
     * @return String webLink
     */
    public String getWebLink() {
        return this.webLink;
    }

    // !! No further public or private (helper) method, should be added to this class!
}
