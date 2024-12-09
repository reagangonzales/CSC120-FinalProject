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
        System.out.println("Aisles in Grocery Store: 3");
        System.out.println("find (ingredient)");
    }
}
