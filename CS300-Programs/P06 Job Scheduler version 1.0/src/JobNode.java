//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Job Scheduler version 1.0
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
public class JobNode {
 // Class Fields
    private static int jobCount = 0; // number of jobs already created
    
    // Object Fields
    private int jobId;          // unique job identifier
    private float arrivalTime;  // arrival time in seconds
    private int userId;         // identifier of the user that created the job
    private int priority;       // job priority
    private int timeToLive;     // job Time To Live in seconds
    private String description; // job description
    
    private JobNode next; // reference to the next job in the linked list
    
    // Constructor using fields
    /**
     * Description of the constructor comes here
     * @param arrivalTime
     * @param userId
     * @param priority
     * @param ttl
     * @param description
     */
    public JobNode(float arrivalTime, int userId, int priority, 
            int ttl, String description) {
        jobCount++;//increment static field jobCount
        this.jobId = jobCount;//assign the unique jobId to each of the jobNode
        this.arrivalTime = arrivalTime;
        this.userId = userId;
        this.priority = priority;
        this.timeToLive = ttl;
        this.description = description;
        
    }
    
    // You can overload your class by other constructors
    // Following are Getters and Setters of the fields.
    public int getJobId() {return this.jobId;}
    public float getArrivalTime() {return this.arrivalTime;}
    public int getPriority() {return this.priority;}
    public int getUserId() {return this.userId;}
    public int getTimeToLive() {return this.timeToLive;}
    public String getDescription() {return this.description;}
    public JobNode getNext() {return this.next;}
    public void setJobId (int jobId) {this.jobId = jobId;}
    public void setNext(JobNode next) {this.next = next;}
    
    /**
     * This method returns a new reference to a copy of the current JobNode
     * @return a new reference to a copy of this (instanceof JobNode)
     */
    public JobNode copy() {
        jobCount--;//since creating one new increments the static field, we decrement it first
        JobNode newNode = new JobNode(this.arrivalTime, this.userId, this.priority, 
                                      this.timeToLive, this.description);
        
        return newNode;
        
    }
}
