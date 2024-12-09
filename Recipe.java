import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<String> ingredients;

    public Recipe() {
        // Initialize the ingredients list, could be empty initially
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}


