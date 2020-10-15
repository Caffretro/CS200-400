/**
 * Filename:   HashTable.java
 * Project:    p3
 * Authors:    Junheng Wang
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   10pm, 10/29/2018
 * Version:    2.0
 * 
 * Credits:    None
 * 
 * Bugs:       For now there seems to be no bug
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/** This is a class of HashTable
* An ArrayList of LinkedLists is used in hashTable, which is buckets. Every LinkedList in the
* hashTable stores a HashNode class storing K key and V value. 
* We use java's hashCode() method to get a index value for generic type K, and mod tableSize
* to get a value representing its place in the table 
* @author Junheng Wang
* 
* @param <K>
* @param <V>
*/


public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
    
    /**
     * Inner class that stores every inserted value. A HashNode will be the form of how information
     * are stored and inserted into the HashTable. Two fields stores key and value individually.
     */
    class HashNode {
        K key;
        V value;
        
        // Constructor
        HashNode (K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public void setKey(K key) {
            this.key = key;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
    }
    
	private ArrayList<LinkedList<HashNode>> table;
	private int size; // The size of how many nodes are inserted into table
	private int tableSize; // The size of how many buckets we currently have
	private double loadFactor; // loadFactor for rehashing
	
	/**
	 * Default no-arg constructor, calls the other constructor with fixed value.
	 */
	public HashTable() {
	    this(7, 0.75);
	}
	
	/**
	 * Setup a table with initialCpacity as given, and record assigned loadFactor. Initial 
	 * size is 0 since no node has been stored.
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public HashTable(int initialCapacity, double loadFactor) {
	    table = new ArrayList<LinkedList<HashNode>>();
	    for (int i = 0; i < initialCapacity; i++) {
	        table.add(new LinkedList<HashNode>());
	    }
	    // Since we are using an ArrayList, we have to insert LinkedLists of the same number as
	    // initialCapacity
	    this.tableSize = initialCapacity;
	    this.size = 0;
        this.loadFactor = loadFactor;
	}
	
	/**
	 * Implemented own hash function which uses java's hashcode() method to generate a number that
	 * can be used to compute its place in hashtable by moding the table size
	 * @param key
	 * @return int value representing its index in the hashtable
	 */
	private int hash (K key) {
	    return Math.abs(key.hashCode()) % this.tableSize;
	    // Since hashCode could be negative.
	}
	
	/**
	 * This method inserts a new HashNode carrying information key and value into hashTable. If 
	 * inserted information's key is null, an IllegalArgumentException will be thrown. Using 
	 * get(key) to examine if the key that we are trying to insert is already in the table. If a
	 * NoSuchElementException is thrown, which represents the information isn't in the table, we
	 * insert a new node; otherwise, no exception will be thrown, and we go through the LinkedList
	 * to find where the node is, switching its value to the value we are inserting. For every put
	 * attempt, we check loadFactor to see if rehashing is needed.
	 * @param key
	 * @param value
	 * @throws IllegalArgumentException
	 */
	@Override
	public void put(K key, V value) throws IllegalArgumentException {
	    try {
	        if (key == null) {
	            throw new IllegalArgumentException("Inserting null is improper");
	            // key is null is invalid
	        }
	        get(key);
	        // Using get(key) to examine if the key exists in the table. 
	        // If it is in the table, a NoSuchElementException will be thrown, and do direct put
	        
	        // If code reaches here, there is a duplicate
	        int place = hash(key);
	        for (int i = 0; i < table.get(place).size(); i++) {
	            if (key.equals(table.get(place).get(i).key)) {
	                table.get(place).get(i).setValue(value);
	                break;
	            }
	        }
	    } catch (NoSuchElementException e) {
	        // Case when the key inserting is not in table
	        table.get(hash(key)).add(new HashNode(key, value));
	        this.size++;
	    }
	    
	    
	    // Check for rehashing
	    if ((1.0 * size) / this.tableSize >= this.loadFactor) {
	        rehash();
	    }
	}
	
	/**
	 * Private helper method for rehashing. Table size will be doubled and plus 1. Copying all the
	 * nodes from current table into the new table. In the end, renew table's reference to enlarged
	 * new table.
	 */
	private void rehash() {
	    ArrayList<LinkedList<HashNode>> tempTable = new ArrayList<LinkedList<HashNode>>();
	    for (int i = 0; i < tableSize * 2 + 1; i++) {
	        tempTable.add(new LinkedList<HashNode>());
	    }
	    int oldTableSize = this.tableSize;
	    // store current table size for loop use
	    this.tableSize = this.tableSize * 2 + 1;
	    // enlarge table size while hash() can use
	    
	    for (int j = 0; j < oldTableSize; j++) {
	        for (int k = 0; k < table.get(j).size(); k++) {
	            HashNode tempNode = table.get(j).get(k);
	            tempTable.get(hash(tempNode.key)).add(tempNode);
	        }
	    }
	    this.table = tempTable;
	    
	}
	
	/**
	 * This is a get method which searches if a node with field key exists in the table. If the key
	 * we are searching is null, an IllegalArgumentException will be thrown. If the key is found, 
	 * the value that pairs with key will be returned, otherwise, a NoSuchElementException will be
	 * thrown to indicate absence
	 * @param key
	 * @return value of the key we are searching
	 * @throws IllagalArgumentException, NoSuchElementException
	 */
	@Override
	public V get(K key) throws IllegalArgumentException, NoSuchElementException {
	    int place = hash(key); // which of the buckets the key is in the ArrayList.
	    int listSize = table.get(place).size(); // How many items in the bucket.
	    
        if (key == null) {
            throw new IllegalArgumentException("Searching null is invalid.");
        }
        for (int i = 0; i < listSize; i++) {
            if (key.equals(table.get(place).get(i).key)) {
                return table.get(place).get(i).value;
            }
        }
        throw new NoSuchElementException("Item can't be found.");
	    
	}
	
	/**
	 * This is a remove method which removes the node with given key in the hashtable. If the key
	 * removing is null, an IllegalArgumentException will be thrown. If the key we are removing
	 * isn't in the table, a NoSuchElementException will be thrown. Otherwise, it will just remove
	 * the node from table.
	 * @param key
	 * @throws IllegalArgumentException, NoSuchElementException
	 */
	@Override
	public void remove(K key) throws IllegalArgumentException, NoSuchElementException {
	    int place = hash(key); // which of the buckets the key is in the ArrayList.
	    int listSize = table.get(place).size(); // How many items in the bucket.
        if (key == null) {
            throw new IllegalArgumentException("Removing null is invalid.");
        }
        
        get(key);
        // Examine if the key we want to remove exists in this table. 
        // If not, throw NoSuchElementException
        
        for (int i = 0; i < table.get(hash(key)).size(); i++){
            if (key.equals(table.get(place).get(i).key)) {
                table.get(place).remove(i);
                break;
                // Find it and remove it, then we just quit the loop, done removing
            }
        }
	    
	}
	
	/**
	 * This methods returns how many nodes are currently in the table.
	 * @return number of nodes in the table
	 */
	@Override
	public int size() {
		return this.size;
	}
}
