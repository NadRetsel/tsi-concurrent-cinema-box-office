import java.util.*;

public class Screening {

    private final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private HashMap<String, Seat[]> allSeats;

    private List<Seat> seatsRemaining;
    private int ticketsRemaining;


    public Screening() {
        this.allSeats = new HashMap<>();
        this.seatsRemaining = new LinkedList<>();
    }

    public int getTicketsRemaining() {
        return this.ticketsRemaining;
    }

    public void createSeats(int[] seatingArrangement) {

        for(int seatRow = 0; seatRow < seatingArrangement.length; ++seatRow)
        {
            int seatsPerRow = seatingArrangement[seatRow];
            String rowLetter = ALPHABET[seatRow];

            Seat[] seats = new Seat[seatsPerRow];
            for(int seatInd = 0; seatInd < seats.length; ++seatInd)
            {
                String seatName = rowLetter + " " + (seatInd + 1);
                Seat seat = new Seat(seatName, true);
                seats[seatInd] = seat;

                this.seatsRemaining.add(seat);
            }

            this.ticketsRemaining += seatsPerRow;
            this.allSeats.put(rowLetter, seats);
        }
    }


    public void printSeats()
    {
        for(String seatRow : this.allSeats.keySet())
        {
            for(Seat seat : this.allSeats.get(seatRow))
            {
                System.out.print(seat.getName() + " | ");
            }
            System.out.println();
        }
    }


    public Seat getRandomSeat() {
        return this.seatsRemaining.get(new Random().nextInt(this.ticketsRemaining));
    }


    public Seat getSeat(String seatName) {

        String[] seatInfo = seatName.split(" ");
        String seatRow = seatInfo[0];
        int seatInd = Integer.parseInt(seatInfo[1]) - 1;

        return this.allSeats.get(seatRow)[seatInd];
    }


    public synchronized Seat bookSeat(Seat seat)
    {
        if(!seat.isAvailable()) return null;

        this.ticketsRemaining -= 1;
        this.seatsRemaining.remove(seat);
        seat.setAvailable(false);

        return seat;
    }

}
