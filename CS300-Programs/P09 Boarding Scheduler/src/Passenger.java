
public class Passenger implements Comparable<Passenger> {
    private String name;
    private int time;
    private String seat;
    private int preferredBoarding;
    public int row;
    public int boardedTime;
    
    public Passenger(String name, int time, String seat) {
        this.name = name;
        this.time = time;
        this.seat = seat;
        this.row = Integer.parseInt(seat.substring(0, seat .length() - 1));
        this.preferredBoarding = 0;
    }
    public Passenger(String name, int time, String seat, int preferredBoarding) {
        this.name = name;
        this.time = time;
        this.seat = seat;
        this.row = Integer.parseInt(seat.substring(0, seat.length() - 1));
        this.preferredBoarding = preferredBoarding;
    }
    public String getName() {
        return this.name;
    }
    public int getTime() {
        return this.time;
    }
    public String getSeat() {
        return this.seat;
    }
    public int getPreferredBoarding() {
        return this.preferredBoarding;
    }
    public String toString() {
        return "" + getName() + " " + getTime() + " " + getSeat() + " " + getPreferredBoarding() + this.row;
    }
    public void setDoneTimeEstimate(int estimate) { 
        
    }
    public void setTimeBoarded
    public int compareTo(Passenger other) {
        if (this.preferredBoarding < other.preferredBoarding) {
            return true;
        }
        else if (this.preferredBoarding == other.preferredBoarding) {
            
        }
        else {
            return false;
        }
    } //Required for implementing Comparable
}