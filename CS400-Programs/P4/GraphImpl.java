import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Filename:   GraphImpl.java
 * Project:    p4
 * Course:     cs400 
 * Authors:    Junheng Wang
 * Due Date:   Nov 19, 2018
 * 
 * T is the label of a vertex, and List<T> is a list of
 * adjacent vertices for that vertex.
 *
 * Additional credits: None.
 *
 * Bugs or other notes: For now, don't know if any bugs exists.
 *
 * @param <T> type of a vertex
 */

/**
 * This is a Graph implementation of GraphADT. It has a map containing all vertices and their 
 * corresponding children in key-value pairs. User can add/remove vertex/edges to/from the graph.
 * It can also get a collection of all vertices or one vertex's all successors, etc. 
 * @author Junheng Wang
 * @param <T>
 */
public class GraphImpl<T> implements GraphADT<T> {

    // YOU MAY ADD ADDITIONAL private members
    // YOU MAY NOT ADD ADDITIONAL public members

    /**
     * Store the vertices and the vertice's adjacent vertices
     */
    private Map<T, List<T>> verticesMap; 
    
    
    /**
     * Construct and initialize and empty Graph
     */ 
    public GraphImpl() {
        this.verticesMap = new HashMap<T, List<T>>();
        // you may initialize additional data members here
    }
    
    /**
     * Adds a vertex to the graph. If the vertex is null or the graph already contains the 
     * vertex, do nothing.
     * @param vertex
     */
    public void addVertex(T vertex) {
        if (vertex != null && !hasVertex(vertex)) {
            verticesMap.put(vertex, new ArrayList<T>());
        }
        // If vertex is null or exists in the map, do nothing
    }
    
    /**
     * Removes a vertex and all edges related to it. If the vertex is null or the graph doesn't
     * contain this vertex, do nothing.
     * @param vertex
     */
    public void removeVertex(T vertex) {
        if (vertex != null && hasVertex(vertex)) {
            for (List<T> list : verticesMap.values()) {
                if (list.contains(vertex)) {
                    list.remove(vertex);
                }
            }// Firstly, remove all inward edges
            verticesMap.remove(vertex);// Removing outward edges and the vertex
        }
        // If vertex is null or exists in the map, do nothing
    }
    
    /**
     * Add a link from vertex1 pointing to vertex2. If either vertex is null or doesn't exist in
     * the graph, do nothing. Moreover, if the link already exists, do nothing.
     * @param vertex1, vertex2
     */
    public void addEdge(T vertex1, T vertex2) {
        if (hasVertex(vertex1) && hasVertex(vertex2)) { // graph contains both vertex
            if (!verticesMap.get(vertex1).contains(vertex2)) { // doesn't contain edge
                verticesMap.get(vertex1).add(vertex2); //adds the vertex to reference list
            }
        }
        // If one of the vertices is null, do nothing
    }
    
    /**
     * Removes a link from vertex 1 pointing to vertex2. If either vertex is null or doesn't exist
     * in the graph, do nothing. Moreover, if the link doens't exist, do nothing.
     * @param vertex1, vertex2
     */
    public void removeEdge(T vertex1, T vertex2) {
        if (hasVertex(vertex1) && hasVertex(vertex2)) { // graph contains both vertex
            if (verticesMap.get(vertex1).contains(vertex2)) { // contains he edge
                verticesMap.get(vertex1).remove(vertex2);
            }
        }
        // If one of the vertices is null, do nothing
    }    
    
    /**
     * Returns the set view of all vertices in the map.
     */
    public Set<T> getAllVertices() {
        return this.verticesMap.keySet();
    }
    
    /**
     * Returns the list of vertex's children in the graph
     * @param vertex
     * @return List<T> containing vertices
     */
    public List<T> getAdjacentVerticesOf(T vertex) {
        return this.verticesMap.get(vertex);
    }
    
    /**
     * Returns boolean value representing if the graph contains the vertex. If the vertex is null,
     * return false directly.
     * @param vertex
     * @return true if the graph contains this vertex
     */
    public boolean hasVertex(T vertex) {
        if (vertex == null) {
            return false;
        }
        return this.verticesMap.containsKey(vertex);
    }
    
    /**
     * Returns number of vertices in the graph.
     * @return int
     */
    public int order() {
        return this.verticesMap.keySet().size();
    }

    /**
     * Returns number of edges in the graph.
     * @return int 
     */
    public int size() {
        Iterator<List<T>> iterator = verticesMap.values().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i += iterator.next().size();
        }
        return i;
    }
    
    
    /**
     * Prints the graph for the reference
     * DO NOT EDIT THIS FUNCTION
     * DO ENSURE THAT YOUR verticesMap is being used 
     * to represent the vertices and edges of this graph.
     */
    public void printGraph() {

        for ( T vertex : verticesMap.keySet() ) {
            if ( verticesMap.get(vertex).size() != 0) {
                for (T edges : verticesMap.get(vertex)) {
                    System.out.println(vertex + " -> " + edges + " ");
                }
            } else {
                System.out.println(vertex + " -> " + " " );
            }
        }
    }
}
