import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        if (!items.contains(item)) {
            items.add(item);
            System.out.println(item + " has been added to your inventory.");
        } else {
            System.out.println("You already have " + item + " in your inventory.");
        }
    }

    public void removeItem(String item) {
        if (items.contains(item)) {
            items.remove(item);
            System.out.println(item + " has been removed from your inventory.");
        } else {
            System.out.println("You don't have " + item + " in your inventory.");
        }
    }

    public boolean hasItem(String item) {
        return items.contains(item);
    }

    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory: " + items);
        }
    }
}
