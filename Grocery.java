import java.util.ArrayList;
import java.util.List;

public class Grocery extends Location {

    private List<String> foundIngredients;

    public Grocery(String address, int nFloors, int nRooms) {
        super("Grocery Store", address, nFloors, nRooms);
        foundIngredients = new ArrayList<>();
    }

    public void findIngredient(String ingredient) {
        if (!foundIngredients.contains(ingredient)) {
            foundIngredients.add(ingredient);
            System.out.println("You found " + ingredient + "!");
        } else {
            System.out.println("You already have " + ingredient + ".");
        }
    }

    public List<String> getIngredients() {
        return this.foundIngredients;
    }

    public void showOptions() {
        super.showOptions();
        System.out.println(" + findIngredient(ingredient) \n + getIngredients() \n + go east, go west, go north");
    }

    public void goEast() {
        System.out.println("You head east to the flour aisle.");
    }

    public void goWest() {
        System.out.println("You head west to the sugar aisle.");
    }

    public void goNorth() {
        System.out.println("You head north to the eggs aisle.");
    }
}
