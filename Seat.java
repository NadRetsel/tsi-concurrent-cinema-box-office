public class Seat {

    private String name;
    private boolean available;

    public Seat(String name, boolean available) {
        this.name = name;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
