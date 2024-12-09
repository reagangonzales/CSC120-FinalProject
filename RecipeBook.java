import java.util.ArrayList;
import java.util.List;

public class RecipeBook {

    private List<String> correctRecipe;

    public RecipeBook() {
        // Ideal recipe
        correctRecipe = new ArrayList<>();
        correctRecipe.add("flour");
        correctRecipe.add("sugar");
        correctRecipe.add("eggs");
        correctRecipe.add("butter");
        correctRecipe.add("chocolate");
        correctRecipe.add("vanilla");
        correctRecipe.add("baking powder");
    }

    public boolean checkRecipeMatch(Recipe currentRecipe) {
        // A simple check to see if all ingredients match in the correct order
        return currentRecipe.getIngredients().equals(correctRecipe);
    }

    public int calculateRecipeAccuracy(Recipe currentRecipe) {
        // Calculate how many ingredients match in the correct recipe
        int correctCount = 0;
        List<String> currentIngredients = currentRecipe.getIngredients();

        for (String ingredient : currentIngredients) {
            if (correctRecipe.contains(ingredient)) {
                correctCount++;
            }
        }

        // Return the percentage of correct ingredients
        return (int) ((double) correctCount / correctRecipe.size() * 100);
    }
}






