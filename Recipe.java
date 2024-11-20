public class Recipe {
    private String name;
    private String[] ingredients;
    private int eggsRequired;

    public Recipe(String name, String[] ingredients, int eggsRequired) {
        this.name = name;
        this.ingredients = ingredients;
        this.eggsRequired = eggsRequired;
    }

    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public int getEggsRequired() {
        return eggsRequired;
    }

    public void displayRecipe() {
        System.out.println("Recipe: " + name);
        System.out.println("Ingredients:");
        System.out.println("Eggs required: ");
        for (String ingredient : ingredients) {
            System.out.println("- " + ingredient);
        }
    }
}



