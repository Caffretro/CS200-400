
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import javax.swing.event.ListSelectionEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Filename:   CourseSchedulerUtil.java
 * Project:    p4
 * Authors:    Debra Deppeler, Junheng Wang
 * 
 * Use this class for implementing Course Planner
 * @param <T> represents type
 */

/**
 * This is a CourseSchedulerUtil class using GraphImpl as the main underlying data structure. It 
 * reads information from a json file containing all courses and their prerequisites, regarding 
 * them as vertices and edges. User can use the scheduler to detect if all courses can be completed
 * , what are prerequisites of a special course, and one of the many sequences of how he/she should
 * choose courses.
 * @author Junheng Wang
 * @param <T> represents type
 */
public class CourseSchedulerUtil<T> {
    
    // can add private but not public members
    
    /**
     * Graph object and array of entities
     */
    private GraphImpl<T> graphImpl; // Graph instance that stores courses information
    private Entity[] entities; // Entity array that stores all courses and their prerequisites
    
    
    /**
     * constructor to initialize a graph object
     */
    public CourseSchedulerUtil() {
        this.graphImpl = new GraphImpl<T>();
        this.entities = new Entity[0];
        // Default initialization
        
    }
    
    /**
    * createEntity method is for parsing the input json file 
    * @return array of Entity object which stores information 
    * about a single course including its name and its prerequisites
    * @throws Exception like FileNotFound, JsonParseException
    * @author Junheng Wang
    */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Entity[] createEntity(String fileName) throws Exception {
        JSONParser parser = new JSONParser();
        Object fileObj = parser.parse(new FileReader(fileName));
        JSONObject jsonObject = (JSONObject) fileObj;
        // Regular JSON parsing procedure
        JSONArray courses = (JSONArray) jsonObject.get("courses"); // Parsed all courses
        ArrayList list = new ArrayList(); // list storing all courses
        
        for (int i = 0; i < courses.size(); i++) {
            JSONObject individual = (JSONObject) courses.get(i);
            String name = (String) individual.get("name");
            ArrayList<String> preq = (ArrayList<String>) individual.get("prerequisites");
            list.add(name);
            for (int j = 0; j < preq.size(); j++) {
                list.add(preq.get(j));
            }
            // Add all courses (regardless it is a prerequisite or not) to a list
        }
        
        Set<T> noDuplicate = new TreeSet<>(list);
        List<T> listNoDuplicate = new ArrayList(noDuplicate);
        this.entities = new Entity[listNoDuplicate.size()];
        // By using the property of a set, which doesn't allow duplicates, we get all courses
        
        for (int i = 0; i < courses.size(); i++) {
            entities[i] = new Entity();
            JSONObject individual = (JSONObject) courses.get(i);
            String name = (String) individual.get("name");
            ArrayList<String> preq = (ArrayList<String>) individual.get("prerequisites");
            entities[i].setName(name);
            entities[i].setPrerequisites(preq.toArray());
            listNoDuplicate.remove(name);
            // Adding all courses that is specified in the json file, and remove them from the 
            // list, so we can add the rest courses without prerequisites
        }
        for (int i = 0; i < listNoDuplicate.size(); i++) {
            entities[i + courses.size()] = new Entity();
            entities[i + courses.size()].setName(listNoDuplicate.get(i));
            entities[i + courses.size()].setPrerequisites(new Object[0]);
            // Adding all other courses doens't specified
        }
        return entities;
        
    }
    
    
    /**
     * Construct a directed graph from the created entity object 
     * @param entities which has information about a single course 
     * including its name and its prerequisites
     * @author Junheng Wang
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void constructGraph(Entity[] entities) {
        for (Entity<T> course : entities) {
            this.graphImpl.addVertex(course.getName()); // Inserting vertices first
        }
        for (Entity<T> course : entities) {
            for (int i = 0; i < course.getPrerequisites().length; i++) {
                this.graphImpl.addEdge(course.getName(), course.getPrerequisites()[i]);
                // Inserting all edges
            }
        }
    }
    
    
    /**
     * Returns all the unique available courses
     * @return the sorted list of all available courses
     * @author Junheng Wang
     */
    public Set<T> getAllCourses() {
        return this.graphImpl.getAllVertices();
    }
    
    
    /**
     * To check whether all given courses can be completed or not
     * @return boolean true if all given courses can be completed,
     * otherwise false
     * @throws Exception
     * @author Junheng Wang
     */
    public boolean canCoursesBeCompleted() throws Exception {
        boolean cycleDetected = false;
        for (Entity<T> entity : this.entities) {
            cycleDetected = cycleDetection((T)entity.getName());
            // Calls helper method and examines the value returned to see if courses can be done
            // We do DFS cycle detection on every node to make sure no cycle exists
            if (cycleDetected) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Given one specific vertex in the graph, this method calls a recursive helper method to see
     * if a cycle exists from the node with a DFS traversal.
     * @param name
     * @return true if cycle detected
     * @author Junheng Wang
     */
    private boolean cycleDetection(T name) {
        if (this.graphImpl.getAdjacentVerticesOf(name).size() == 0) {
            return false;
            // If a vertex has no edges, return false since there cannot be a cycle
        } else {
            HashSet<T> preq = new HashSet<T>();
            // Set can prevent from creating duplicates
            try {
                preq = recCycleDetection(name, preq);
                // recursively detecting cycle
            } catch(IllegalArgumentException e) {
                return true;
                // Case when cycle detected
            }
            return false;
            // Case when no cycle detected
        }
    }
    
    /**
     * This is a recursive method performing DFS algorithm with one vertex and a list of boolean
     * to check if vertices are visited.
     * @param name
     * @param preq
     * @return HashSet<T>
     * @author Junheng Wang
     */
    private HashSet<T> recCycleDetection(T name, HashSet<T> preq) {
        if (this.graphImpl.getAdjacentVerticesOf(name).size() == 0) {
            return preq;
            // Where no more children
        } else {
            List<T> temp = this.graphImpl.getAdjacentVerticesOf(name);
            for (T course : temp) {
                if (preq.contains(course)) {
                    throw new IllegalArgumentException();
                    // When we detected a cycle
                }
                preq.add(course);
                preq = recCycleDetection(course, preq);
            }
            return preq;
        }
    }
    
    
    /**
     * The order of courses in which the courses has to be taken
     * @return the list of courses in the order it has to be taken
     * @throws Exception when courses can't be completed in any order
     * @author Junheng Wang
     */
    public List<T> getSubjectOrder() throws Exception {
        if(!this.canCoursesBeCompleted()) {
            throw new Exception("Cycle exists and there cannot be an order.");
        }
        Stack<T> stack = new Stack<T>();
        Map<T, Boolean> visited = new HashMap<T, Boolean>();
        for (Entity entity : this.entities) {
            visited.put((T)entity.getName(), false);
        }
        // Initializing all vertices as unvisited
        for (Entity entity : this.entities) {
            if (!visited.get(entity.getName())) {
                recTopological((T)entity.getName(), visited, stack);
            }
        }
        // Doing a topological order algorithm, while pushing vertices to a stack for further uses
        List<T> order = new ArrayList<T>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        // Storing all vertices in reversed topological order since we are just popping from stack
        Collections.reverse(order); // Reversing order so we can use
        return order;

    }
    
    /**
     * This is a topological ordering algorithm realization with a stack
     * @param v
     * @param visited
     * @param stack
     * @author Junheng Wang
     */
    private void recTopological(T v, Map<T, Boolean> visited, Stack<T> stack) {
        visited.put(v, true);
        T n;
        Iterator<T> iter = this.graphImpl.getAdjacentVerticesOf(v).listIterator();
        while(iter.hasNext()) {
            n = iter.next();
            if (!visited.get(n)) {
                recTopological(n, visited, stack);
            }
        }
        stack.push(v);
    }

        
    /**
     * The minimum course required to be taken for a given course
     * @param courseName 
     * @return the number of minimum courses needed for a given course
     * @author Junheng Wang
     */
    public int getMinimalCourseCompletion(T courseName) throws Exception {
        if (cycleDetection(courseName)) {
            return -1;
            // If from the course given, we can detect a cycle, just return -1
        } else if (this.graphImpl.getAdjacentVerticesOf(courseName).size() == 0){
            return 0;
            // Case where the course given has no prerequisites
        } else {
            Set<T> set = new HashSet<T>();
            return recMinimal(courseName, set);
            // Case where we need a traversal to see how many courses need to be done
            // Notice that duplicate courses doesn't count, so we use a map
        }
        
    }
    
    /**
     * This is a recursive method counting the number of vertices on a part of the graph given one
     * specific node
     * @param v
     * @param prereqMap
     * @return number of prerequisites for a given course
     * @author Junheng Wang
     */
    private int recMinimal(T v, Set<T> set) {
        if (this.graphImpl.getAdjacentVerticesOf(v).size() == 0) {
            return 0;
            // Base case
        } else {
            T n;
            Iterator<T> iter = this.graphImpl.getAdjacentVerticesOf(v).listIterator();
            while(iter.hasNext()) {
                n = iter.next();
                set.add(n);
            }
            // First insert all prerequisites of the current course
            Iterator<T> iter2 = this.graphImpl.getAdjacentVerticesOf(v).listIterator();
            while(iter2.hasNext()) {
                n = iter2.next();
                recMinimal(n, set);
            }
            // Do a recursive count
            return set.size();
        }
    }
    
// Below was for test use
    
//    public static void main(String[] args) throws Exception{
//         CourseSchedulerUtil function = new CourseSchedulerUtil();
//         function.createEntity("valid.json");
//         function.constructGraph(function.entities);
//         System.out.println(function.getAllCourses());
//         System.out.println(function.canCoursesBeCompleted());
//         System.out.println(function.getSubjectOrder());
//         System.out.println(function.getMinimalCourseCompletion("CS790"));
//
//    }
    
}
