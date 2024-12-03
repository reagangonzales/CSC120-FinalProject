public class Player {
    private String name;
    private Inventory inventory;
    private Location currentLocation;
    private double money; // Add money attribute

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    // Getter and setter for money
    public double getMoney() {
        return money;
    }

    public void addMoney(double amount) {
        this.money += amount;
    }

    // constructor that takes name and initial location
    public Player(String name, Location initialLocation) {
        this.name = name;
        this.inventory = new Inventory();
        this.currentLocation = initialLocation;
        System.out.println("Player " + name + " starts at " + currentLocation.getName());
    }
    

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
        System.out.println("You are now at: " + currentLocation.getName());
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public void performAction(String action) {
        if (action.equalsIgnoreCase("inventory")) {
            inventory.showInventory();
        } else {
            System.out.println("Unknown action: " + action);
        }
    }
}
