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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JobScheduler {
    private JobLList jobList;
    
    public JobScheduler() {
        jobList = new JobLList();
    }
    public JobLList getJobList() {return this.jobList;}
    /**
     * startScheduling method reads a trace file of name 
     * tracefilename, parses its content line by line. 
     * Each line represents data related to an arriving job,
     * Before scheduling any job (aka placing it in the list),
     * this method cleans the list and displays the number 
     * of removed jobs.
     * @param tracefilename
     */
    public void startScheduling(String tracefilename) {
        FileReader fr = null; // reference to a FileReader object
        BufferedReader br = null; // reference to a BufferedReader object
        try{
            // Open the file
            String fileFolder = "files";
            fr = new FileReader(fileFolder+ File.separatorChar + tracefilename);
            br = new BufferedReader(fr);
            String line = null;
            
            // Parse the content of the trace file line by line
            while( (line = br.readLine()) != null){
                // extract the data related to the arriving job
                String[] contents = line.split(" ");
                float arrivalTime = Float.parseFloat(contents[0]);
                int userId = Integer.parseInt(contents[1]);
                int priority = Integer.parseInt(contents[2]);
                int timeToLive = Integer.parseInt(contents[3]);
                StringBuilder str = new StringBuilder("");
                for(int i = 4; i < contents.length; i++){
                    str.append(contents[i]);
                    str.append(" ");
                }           
                String description = str.toString().trim();
                
                //create a new job instanceof JobNode class
                JobNode objJob = new JobNode(arrivalTime, userId, 
                        priority, timeToLive, description);             
                            
                System.out.println("Job Waiting List Status at " 
                        + objJob.getArrivalTime() + "s");
                System.out.println("*************************************");    
                    
                // Before adding the new job, let's remove the obsolete jobs 
                // in the list and display the number of cleaned jobs
                int cleanedJobCount = jobList.clean(arrivalTime);
                System.out.println(cleanedJobCount + 
                            " obsolete job(s) removed from the list.");
                
                // Schedule the created new job
                jobList.schedule(objJob); // add the new JobNode to the list
                                          // according to the scheduling policy
                System.out.println("Event: Job #" + objJob.getJobId() +
                                 " added to the list.");
                    
                // Print the content of the list
                System.out.println(jobList.toString());
            }
                
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
    }
        finally { // close open resources
            if (br != null)
                try {
                    br.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
     // Display Welcome Message
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("        Job Scheduler version 1.0");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                
        // Create a JobScheduler object
        JobScheduler myJobScheduler = new JobScheduler();
         
        // Print the list
        System.out.println("Initial Job Waiting List Status");
        System.out.println("*******************************");
        System.out.println(myJobScheduler.getJobList().toString());
                
        // Parse the trace file and start scheduling simulation
        myJobScheduler.startScheduling("jobs.txt");
                
        // Duplicate List
        JobLList copyJobList = (JobLList)myJobScheduler.getJobList().duplicate();
                
        // Display Duplicated list content
        System.out.println("Duplicate List Status");
        System.out.println("*********************");
        System.out.println(copyJobList.toString());     
                
        // Clear the original list
        System.out.println("Clear Original List");
        System.out.println("*******************");
        myJobScheduler.getJobList().clear();
                
        // Display the content of the original list
        System.out.println(myJobScheduler.getJobList().toString());
                
        // END
        System.out.println(" -------- END Scheduling Simulation --------");
    }
}
