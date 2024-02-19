import java.util.Arrays;
import java.util.LinkedList;

public class Customer implements Runnable {

    private String name;
    private Screening screening;
    private LinkedList<Seat> seatsBooked;
    private LinkedList<String> seatsDesired;

    public Customer(String name, Screening screening, LinkedList<String> seatsDesired) {
        this.name = name;
        this.screening = screening;
        this.seatsDesired = seatsDesired;

        this.seatsBooked = new LinkedList<>();
    }


    public LinkedList<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public LinkedList<String> getSeatsDesired() {
        return seatsDesired;
    }


    public void setSeatsBooked(LinkedList<Seat> seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public void setSeatsDesired(LinkedList<String> seatsDesired) {
        this.seatsDesired = seatsDesired;
    }


    public void run() {

        for(String seatName : this.seatsDesired)
        {
            Seat seat = this.screening.getSeat(seatName);
            if(!seat.isAvailable()) continue;

            System.out.println("Customer " + this.name + ": Attempting to book seat [" + seatName + "].");
            Seat bookedSeat = this.screening.bookSeat(seat);


            if(null == bookedSeat) {
                System.out.println("Customer " + this.name + ": Seat [" + seatName + "] is already booked.");
                continue;
            }

            this.seatsBooked.add(bookedSeat);
            System.out.println("Customer " + this.name + ": Booked seat [" + seatName + "].");

        }
    }


    public void printCustomer() {
        System.out.println("Tickets booked by Customer " + this.name);
        System.out.println(Arrays.toString(seatsBooked.stream()
                .map(Seat::getName)
                .toArray()));
    }

}
