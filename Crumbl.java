import java.util.HashMap;
import java.util.Map;

public class Crumbl extends Location {

    private Map<String, Boolean> stations;
    private boolean hasBlended;
    private boolean hasBaked;
    private boolean hasDecorated;
    private boolean isBaking;

    public Crumbl(String address, int nFloors, int nRooms) {
        super("Crumbl Shop", address, nFloors, nRooms);
        stations = new HashMap<>();
        stations.put("oven", false);
        stations.put("blender", false);
        stations.put("decorating station", false);
        hasBlended = false;
        hasBaked = false;
        hasDecorated = false;
        isBaking = false;
    }

    public void showOptions() {
        super.showOptions();
        System.out.println("Stations in Crumbl: 3");
        System.out.println("\n+ blend \n + bake cookie \n + decorate cookie + \n + sell cookie");
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
        if (isBaking) {
            System.out.println("The cookie is already baking!");
        } else {
            // Start baking the cookie
            System.out.println("Baking the cookie...");
            isBaking = true;

            // Simulate baking time
            // Source: https://www.baeldung.com/java-delay-code-execution
            try {
                Thread.sleep(3000); // This simulates the time it takes to bake the cookie (3 seconds)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // After baking is complete, notify the player
            System.out.println("Cookie is done baking!");

            // Reset the baking status for the next round
            isBaking = false;
        }
    }

    public void decorateCookie() {
        hasDecorated = true; 
        System.out.println("You decorated the cookie beautifully!");
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

    public void resetCookieState() {
        hasBlended = false;
        hasBaked = false;
        hasDecorated = false;
    }
}

