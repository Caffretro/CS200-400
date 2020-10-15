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
import java.io.StringWriter;

public class JobLList implements WaitingListADT<JobNode> {
    private JobNode head;
    private int size;
    // We can use Copy in JobNode for implementation of duplicate

    public JobLList() { // no-arg constructor
        head = null;
        size = 0;
    }

    public JobLList(JobNode newHead, int newSize) { // yes-arg constructor
        this.head = newHead;
        this.size = newSize;
    }

    public void schedule(JobNode newJob) {
        JobNode runner = head;
        // for going through the list
        if (newJob.getPriority() == 0) { // low priority case
            if (this.head == null) { // where the list has no node inside of it
                this.head = newJob;
            } else {
                while (runner.getNext() != null) {
                    runner = runner.getNext();
                } // adding to the end, so we go to the end of the list
                newJob.setNext(runner.getNext());// pointing newJob to the tail of the list
                runner.setNext(newJob);// connecting the original tail node to the newJob node
            }
        } else { // high priority case
            if (this.head == null) { // also, the situation where the list is empty
                this.head = newJob;
            } else {
                // special situation where all the nodes are low priority
                if (head.getPriority() == 0) {
                    newJob.setNext(head);
                    head = newJob;
                } else {
                    while (runner.getNext() != null && runner.getNext().getPriority() == 1) {
                        runner = runner.getNext();
                    } // go until the next node is low priority
                    newJob.setNext(runner.getNext());
                    // point the next of newJobe to the first low priority one
                    runner.setNext(newJob);
                    // connecting
                }

            }
        }
        size++;// increment the size of the list
    }

    public int clean(float cleaningTime) {
        JobNode runner = this.head;
        if (this.size == 0) {
            return 0;// special case where the list is empty
        } else {
            int cleanNum = 0;
            int steps = 0;
            do {
                // the condition where if the node should be removed
                if (runner.getArrivalTime() + runner.getTimeToLive() < cleaningTime) {
                    this.remove(steps);// used our own helper method to remove
                    steps--;// cause one of the node was removed, we have to go back one step
                    cleanNum++;// increment the total number of nodes removed
                }
                runner = runner.getNext();// go the next
                steps++;// go to the next step
            } while (runner != null);
            return cleanNum;
        }
    }

    /**
     * This is a helper method which removes a certain node from the list. The function is just like
     * the remove we did in class
     * 
     * @param index
     */
    public void remove(int index) {
        JobNode previousJob = head;
        if (index == 0) {// special case where the first node should be removed
            this.head = head.getNext();
            this.size--;
        } else {
            int steps = 0;
            while (previousJob != null) {
                if (steps == index - 1) {// where the next node should be removed
                    if (previousJob.getNext() != null && previousJob.getNext().getNext() != null) {
                        // where the next is not the tail of the list
                        previousJob.setNext(previousJob.getNext().getNext());
                    } else {// where we are at the tail of the list
                        previousJob.setNext(null);
                    }
                    this.size--;
                    return;
                }
                previousJob = previousJob.getNext();// go the next node
                steps++;// increment the steps
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        int steps = 0;
        JobNode check = head;
        while (check != null) {
            check = check.getNext();
            steps++;
        }
        return steps;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public WaitingListADT<JobNode> duplicate() {
        JobLList copyList = new JobLList();
        copyList.head = this.head.copy();// we don't want to mess up the current list
        copyList.size = 1;
        JobNode runner = this.head;
        JobNode runnerCopy = copyList.head;
        // two different runners running each of the list
        for (int i = 1; i < size; i++) {
            // because the head is already set, we go from the second node
            runnerCopy.setJobId(runner.getJobId());// copy the jobId
            runnerCopy.setNext(runner.getNext().copy());// copy the next reference
            copyList.size++;// increment the size of the copy list
            runnerCopy = runnerCopy.getNext();
            runner = runner.getNext();
            // both of the runner go one step further
        }
        // little problem on why the last node's jobId wasn't renewed, but it works fine
        runnerCopy.setJobId(runner.getJobId());
        return copyList;
    }

    public String toString() {
        StringWriter sw = new StringWriter();
        sw.write("Job List is empty: " + isEmpty() + "\n");
        sw.write("The size is: " + size() + " job(s).\n");
        JobNode runner = head;
        if (runner == null) {
            return sw.toString();
        } else {
            while (runner != null) {
                sw.write("job #" + runner.getJobId() + " : " + runner.getDescription() + " (UID "
                    + runner.getUserId() + ") " + runner.getPriority() + "\n");
                runner = runner.getNext();
            }
            return sw.toString();
        }
        // this is just easy to see, string += sth is also a choice, anyway...

    }
}
