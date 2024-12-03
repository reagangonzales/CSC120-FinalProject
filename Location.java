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

    public int getFloors() {
        return this.nFloors;
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

    public Location exit() {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Building. Must call enter() before exit().");
        }
        if (this.activeFloor > 1) {
            throw new RuntimeException("You have fallen out a window from floor #" +this.activeFloor + "!");
        }
        System.out.println("You have left " + this.name + ".");
        this.activeFloor = -1; // We're leaving the building, so we no longer have a valid active floor
        return null; // We're outside now, so the building is null
    }

    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    }

    public void goUp() {
        this.goToFloor(this.activeFloor + 1);
    }

    public void goDown() {
        this.goToFloor(this.activeFloor - 1);
    }

    public void goEast() {
        System.out.println("You head east");
    }
    
    public void goWest() {
        if (this.name.equals("Car")) {
            System.out.println("You head west");
        } else if (this.name.equals("Grocery Store")) {
            System.out.println("You head west");
        } else if (this.name.equals("Crumbl Shop")) {
            System.out.println("You head west");
        } else {
            System.out.println("There's nothing to the west from here.");
        }
    }
    
    public void goNorth() {
        if (this.name.equals("Car")) {
            System.out.println("You head north");
        } else if (this.name.equals("House")) {
            System.out.println("You head north");
        } else if (this.name.equals("Crumbl Shop")) {
            System.out.println("You head north");
        } else {
            System.out.println("There's nothing to the north from here.");
        }
    }
    
    public void goSouth() {
        if (this.name.equals("Crumbl Shop")) {
            System.out.println("You head south");
        } else if (this.name.equals("Car")) {
            System.out.println("You head south");
        } else if (this.name.equals("Grocery Store")) {
            System.out.println("You head south");
        } else {
            System.out.println("There's nothing to the south from here.");
        }
    }
    

    public void showOptions() {
        System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor(n) + \n goEast() + \n goWest() + \n goNorth() + \n goSouth()");
    }

    public String toString() {
        return this.name + " is a " + this.nFloors + "-story building located at " + this.address + "with" + this.nRooms + "rooms.";
    }

}