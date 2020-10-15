/**
 * Filename:   Profile.java
 * Project:    p3
 * Authors:    Junheng Wang
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   10pm, 10/29/2018
 * Version:    1.0
 * 
 * Credits:    None
 * 
 * Bugs:       For now there seems to be no bugs.
 */
import java.util.TreeMap;

/**
 * This is a profile used to generate a comparison between java's TreeMap and our HashTable. We'll
 * insert and retrieve the same amount of times,(decided by the parseInted arg[0]) and use 
 * Java Flight Recorder to see the usage of resources and time of each datatype. Data generated 
 * will be stored in .jrs files.
 * @author Junheng Wang
 *
 * @param <K>
 * @param <V>
 */
public class Profile<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;
	
	public Profile() {
		// Instantiate hashtable and treemap
	    hashtable = new HashTable<K, V>();
	    treemap = new TreeMap<K, V>();
	}
	
	public void insert(K key, V value) {
		// Insert K, V into both hashtable and treemap
	    this.hashtable.put(key, value);
	    this.treemap.put(key, value);
	}
	
	public void retrieve(K key) {
		// get value V for key K from both hashtable and treemap
	    this.hashtable.get(key);
	    this.treemap.get(key);
	}
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Expected 1 argument: <num_elements>");
			System.exit(1);
		}
		int numElements = Integer.parseInt(args[0]);
		// numElements will be used for loop times.
		Profile<Integer, Integer> profile = new Profile<Integer, Integer>();
		
		for (int i = 0; i < numElements; i++) {
		    profile.insert(i, i);
		    // Comparing insert efficiency.
		}
		for (int j = 0; j < numElements; j++) {
		    profile.retrieve(j);
		    //Comparing get efficiency.
		}
		
		String msg = String.format("Successfully inserted and retreived %d elements into the hash table and treemap", numElements);
		System.out.println(msg);
	}
}
