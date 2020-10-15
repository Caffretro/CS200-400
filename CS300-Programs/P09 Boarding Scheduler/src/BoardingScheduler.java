import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BoardingScheduler {

    /**
     * Reads in a file containing a list of flight passengers in the order they check in and runs
     * the boardFlight() method with those passengers.
     * 
     * @author Tina, Alexi
     * @param flight is the name of the input file in the project directory
     */
    private ArrayList<Passenger> dequeuedInfo;
    private int time = 0;
    BoardingHeap waitList = new BoardingHeap();

    public static void boardFlight(Iterator<Passenger> passengers, int startTime) {
        BoardingScheduler schedule = new BoardingScheduler();

        Passenger temp;
        // Now we load the passengers to the check-in List
        while (passengers.hasNext()) {
            schedule.enqueueNow(passengers);
            schedule.waitList.dequeue();


            // based on each passenger's time field, we enqueue all of those who satisfies this
            // requirement into the heap
            // kind of the idea that the first one enqueued always has time 6, by 1min boarding and
            // 5min sitting
            // and each time we enqueue, we calculateDoneTimeEstimate
            // the one with highest minutes of sitting is the highest priority£¬since they won't
            // block the people behind them.
            // enqueue and dequeue, FIXME
            schedule.time++; // increment time FIXME
        }

        while (passengers.hasNext()) {
            System.out.println(passengers.next().toString());
        }
    }

    private void enqueueNow(Iterator<Passenger> passengers) {
        Passenger temp = passengers.next();
        Passenger temp2 = temp;
        while (passengers.hasNext()) {
            this.waitList.enqueue(temp);
            temp.setDoneTimeEstimate(calculateDoneTimeEstimate(temp));
            // sets the passenger's done time estimate
            temp = passengers.next();
            if (temp.getTime() == temp2.getTime()) {
                continue;
            }
            else {
                break;
            }
        }

    }

    public int calculateDoneTimeEstimate(Passenger p) {
       
    }

    public static void checkIn(String flight) {
        File f = new File(flight);
        try {
            Scanner s = new Scanner(f);
            List<Passenger> passengers = new ArrayList<Passenger>();
            while (s.hasNextLine()) {
                // Data are separated by commas and possibly also whitespace.
                String[] line = s.nextLine().split("\\s*,\\s*");
                if (line.length == 3) // Default preferredBoarding 0 constructor
                    passengers.add(new Passenger(line[0], Integer.parseInt(line[1]), line[2]));
                else // Use the preferredBoarding number if given
                    passengers.add(new Passenger(line[0], Integer.parseInt(line[1]), line[2],
                        Integer.parseInt(line[3])));
            }
            s.close();
            boardFlight(passengers.iterator());
        } catch (IOException e) {
            System.out.println("Error: Unable to load file " + flight);
        }
    }

}
