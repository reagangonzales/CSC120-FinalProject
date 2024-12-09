// I used the A7 Building class to create this class
public class Location {

    protected String name;
    protected String address;
    protected int nFloors;
    protected int activeFloor = -1; // Default value indicating we are not inside this building
    protected int nRooms;

    /* Default constructor */
    public Location() {
        this("<Name Unknown>", "<Address Unknown>", 1,1);
    }

    /* Overload constructor with address only */
    public Location(String address) {
        this(); // Call default constructor
        this.address = address; // Override address
    }

    /* Overload constructor with name, address */
    public Location(String name, String address) {
        this(name, address, 1,1); // Call full constructor with hard-coded # floors
    }

    /* Full constructor */
    public Location(String name, String address, int nFloors, int nRooms) {
        if (name != null) { this.name = name; }
        if (address != null) { this.address = address; } 
        if (nFloors < 1) {
            throw new RuntimeException("Cannot construct a building with fewer than 1 floor.");
        }
        this.nFloors = nFloors;
        this.nRooms = nRooms;
    }

    /* Accessors */
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }
    
    public int getRooms() {
        return this.nRooms;
    }

    /* Navigation methods */
    public Location enter() {
        if (activeFloor != -1) {
            throw new RuntimeException("You are already inside this Building.");
        }
        this.activeFloor = 1;
        System.out.println("You are now inside " + this.name + " on the ground floor.");
        return this; // Return a pointer to the current building
    }

    public void goEast() {
        ;
    }
    
    public void goWest() {
        ;
    }
    
    public void goNorth() {
        ;
    }
    
    public void goSouth() {
        ;
    }
    

    public void showOptions() {
        System.out.println("Available options at " + this.name + ":\n + enter \n + go east \n + go west \n + go north \n + go south");
    }

    public String toString() {
        return this.name + " is a " + this.nFloors + "-story building located at " + this.address + "with" + this.nRooms + "rooms.";
    }

}