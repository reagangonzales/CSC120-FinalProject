import java.util.HashMap;
import java.util.Map;

public class Crumbl extends Location {

    private Map<String, Boolean> stations;
    private boolean hasBlended;
    private boolean hasBaked;
    private boolean hasDecorated;

    public Crumbl(String address, int nFloors, int nRooms) {
        super("Crumbl Shop", address, nFloors, nRooms);
        stations = new HashMap<>();
        stations.put("oven", false);
        stations.put("blender", false);
        stations.put("decorating station", false);
        hasBlended = false;
        hasBaked = false;
        hasDecorated = false;
    }

    public void useBlender(int eggs, Player player) {
        if (player.getInventory().hasItem("flour") &&
            player.getInventory().hasItem("sugar") &&
            player.getInventory().hasItem("eggs")) {
    
            player.getInventory().removeItem("flour");
            player.getInventory().removeItem("sugar");
            for (int i = 0; i < eggs; i++) {
                player.getInventory().removeItem("eggs");
            }
            hasBlended = true;
            System.out.println("You blended the perfect mix with " + eggs + " eggs!");
        } else {
            System.out.println("You don't have all the ingredients to blend.");
        }
    }

    public void bakeCookie() {
        if (hasBlended) {
            hasBaked = true;
            System.out.println("You baked the cookie! It smells amazing.");
        } else {
            System.out.println("You need to blend ingredients first.");
        }
    }

    public void decorateCookie() {
        if (hasBaked) {
            hasDecorated = true;
            System.out.println("You decorated the cookie beautifully!");
        } else {
            System.out.println("You need to bake the cookie first.");
        }
    }

    public int sellCookie(int eggs, Player player) {
        if (isReadyToSell()) {
            int price = eggs * 10;
            System.out.println("You sold the cookie for $" + price + "!");
            resetCookieState(); // Reset for the next batch
            player.addMoney(price); // Add money to the player's balance
            return price;
        } else {
            System.out.println("Your cookie isn't ready to sell yet.");
            return 0;
        }
    }

    public boolean isReadyToSell() {
        return hasBlended && hasBaked && hasDecorated;
    }

    private void resetCookieState() {
        hasBlended = false;
        hasBaked = false;
        hasDecorated = false;
    }
}

