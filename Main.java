import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    static LinkedList<Thread> customerThreads = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {

        Screening screening = createScreening();
        LinkedList<Customer> customers = createCustomers(screening, 5);

        for(Customer customer : customers)
        {
            Thread customerThread = new Thread(customer);
            customerThreads.add(customerThread);
        }


        for(Thread thread : customerThreads) thread.start();

        for(Thread thread : customerThreads) thread.join();

        System.out.println();
        for(Customer customer : customers) customer.printCustomer();
    }


    public static Screening createScreening() {
        Screening screening = new Screening();
        int[] seatingArrangement = {10,10,10,10,15,15,15,25,25,25};
        screening.createSeats(seatingArrangement);

        return screening;
    }


    public static LinkedList<Customer> createCustomers(Screening screening, int totalCustomers) {
        LinkedList<Customer> customers = new LinkedList<>();

        for(int i = 0; i < totalCustomers; ++i)
        {
            Customer customer = new Customer(
                    String.valueOf(i+1),
                    screening,
                    new LinkedList<>(Arrays.asList("A 1", "B 1", "C 1", "D 1", "E 1", "F 1", "G 1", "H 1", "I 1"))
            );
            customers.add(customer);
        }

        return customers;
    }
}
