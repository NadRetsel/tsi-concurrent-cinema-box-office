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
            customerThread.start();
            customerThreads.add(customerThread);
        }


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
        LinkedList<String> desiredSeats = new LinkedList<>(Arrays.asList(new String[]{"A 1", "B 1", "C 1", "D 1", "E 1"}));

        for(int i = 0; i < totalCustomers; ++i)
        {
            Customer customer = new Customer((i+1) + "", screening, desiredSeats);
            customers.add(customer);
        }

        return customers;
    }
}
