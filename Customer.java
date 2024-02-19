import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Customer implements Runnable {

    private String name;
    private Screening screening;
    private LinkedList<Seat> seatsBooked;
    private LinkedList<String> seatsDesired;
    private int ticketsDesired;

    public Customer(String name, Screening screening, LinkedList<String> seatsDesired) {
        this(name, screening, seatsDesired, seatsDesired.size());
    }

    public Customer(String name, Screening screening, LinkedList<String> seatsDesired, int ticketsDesired) {
        this.name = name;
        this.screening = screening;

        Collections.shuffle(seatsDesired);
        this.seatsDesired = seatsDesired;

        this.ticketsDesired = seatsDesired.size();

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

        while(this.getSeatsBooked().size() != this.ticketsDesired
                && this.screening.getTicketsRemaining() > 0)
        {


            Seat seat = !this.seatsDesired.isEmpty()
                    ? this.screening.getSeat(this.seatsDesired.getFirst())
                    : this.screening.getRandomSeat();

            if (!this.seatsDesired.isEmpty()) this.seatsDesired.removeFirst();


            System.out.println("Customer " + this.name + ": Attempting to book seat [" + seat.getName() + "].");
            Seat bookedSeat = this.screening.bookSeat(seat);

            if(null == bookedSeat)
            {
                System.out.println("Customer " + this.name + ": Seat [" + seat.getName() + "] is already booked.");
                continue;
            }

            this.seatsBooked.add(bookedSeat);
            System.out.println("Customer " + this.name + ": Booked seat [" + seat.getName() + "].");
        }







    }


    public void printCustomer() {
        System.out.println("Tickets booked by Customer " + this.name);
        System.out.println(Arrays.toString(this.seatsBooked.stream()
                .map(Seat::getName)
                .toArray()));
    }

}
