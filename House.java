import java.util.HashMap;
import java.util.Map;

public class House extends Location {

    private Map<String, String> itemsLocation;  // Stores items and their locations
    private String currentRoom;  // Tracks the player's current room
    private Player player;  // Reference to the player


    public House(String address, int nFloors, int nRooms) {
        super("House", address, nFloors, nRooms);
        this.itemsLocation = new HashMap<>();
        this.currentRoom = "entrance";  // Start at the entrance
        this.player = null;
        initializeItems();
    }

    // Initialize the locations of the keys and phone
    private void initializeItems() {
        itemsLocation.put("keys", "kitchen");
        itemsLocation.put("phone", "living room");
    }

    public void setPlayer(Player player) {
        this.player = player;  // Set player reference
    }

    // Search for an item in the current room
    public boolean findItem(String item) {
        if (itemsLocation.containsKey(item.toLowerCase()) &&
            itemsLocation.get(item.toLowerCase()).equalsIgnoreCase(currentRoom)) {
            System.out.println("You found your " + item + " in the " + currentRoom + "!");
            itemsLocation.remove(item.toLowerCase());  // Remove item after finding it
            player.getInventory().addItem(item);  // Add item to player's inventory
            return true;
        } else {
            System.out.println("No " + item + " found in the " + currentRoom + ".");
            return false;
        }
    }


    // Navigation within the house
    public void goNorth() {
        if (currentRoom.equals("entrance")) {
            currentRoom = "living room";
        } else if (currentRoom.equals("living room")) {
            currentRoom = "kitchen";
        } else {
            System.out.println("You can't go north from here.");
            return;
        }
        System.out.println("You are now in the " + currentRoom + ".");
    }

    public void goSouth() {
        if (currentRoom.equals("kitchen")) {
            currentRoom = "living room";
        } else if (currentRoom.equals("living room")) {
            currentRoom = "entrance";
        } else {
            System.out.println("You can't go south from here.");
            return;
        }
        System.out.println("You are now in the " + currentRoom + ".");
    }

    public void goEast() {
        System.out.println("There is no room to the east.");
    }

    public void goWest() {
        System.out.println("There is no room to the west.");
    }

    public boolean isReadyToLeave() {
        if (itemsLocation.isEmpty()) {
            System.out.println("You have everything you need. You can now leave the house.");
            return true;
        } else {
            System.out.println("You still need to find some items before leaving.");
            return false;
        }
    }

    public void showOptions() {
        super.showOptions();
        System.out.println("Available rooms: entrance, living room, kitchen");
        System.out.println(" + findItem(item)");
        System.out.println(" + isReadyToLeave()");
    }
}
