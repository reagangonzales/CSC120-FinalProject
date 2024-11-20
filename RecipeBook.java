import java.util.HashMap;
import java.util.Map;

public class RecipeBook {
    private Map<String, Recipe> recipeBook;

    public RecipeBook() {
        recipeBook = new HashMap<>();
        
        // Adding recipes to the book with their ingredients
        recipeBook.put("Peanut Butter ft. Reese’s PB Cup", new Recipe("Peanut Butter ft. Reese’s PB Cup", 
                new String[] {"brown sugar", "Reese’s cups", "butter", "peanut butter", "vanilla", "eggs", "flour", "baking soda", "corn starch"}, 2));
        
        recipeBook.put("Funfetti", new Recipe("Funfetti", 
                new String[] {"butter", "sugar", "egg", "vanilla extract", "flour", "baking powder", "salt", "sprinkles"}, 1));
        
        recipeBook.put("Chocolate ft. OREO", new Recipe("Chocolate ft. OREO", 
                new String[] {"brown sugar", "oreo crumbs", "butter", "sugar", "eggs", "flour", "vanilla", "salt", "baking powder", "baking soda", "cocoa"}, 2));
        
        recipeBook.put("Buckeye Brownie", new Recipe("Buckeye Brownie", 
                new String[] {"peanut butter", "butter", "powdered sugar", "brown sugar", "eggs", "vanilla", "cocoa", "flour", "baking soda", "salt", "semi-sweet chocolate chips"}, 2));
        
        recipeBook.put("Smores", new Recipe("Smores", 
                new String[] {"butter", "vanilla", "milk chocolate chips", "marshmallows", "eggs", "graham crackers", "brown sugar", "baking soda", "salt", "sugar", "flour"}, 2));
        
        recipeBook.put("Blue Monster ft. Chips Ahoy", new Recipe("Blue Monster ft. Chips Ahoy", 
                new String[] {"brown sugar", "chips ahoy cookies", "butter", "eggs", "sugar", "milk chocolate chips", "flour", "corn starch", "salt", "baking powder"}, 2));
        
        recipeBook.put("Snickerdoodle", new Recipe("Snickerdoodle", 
                new String[] {"flour", "butter", "baking soda", "vanilla", "salt", "sugar", "eggs", "sugar", "cinnamon"}, 1));
        
        recipeBook.put("Cinnamon ft. Cinnamon Toast Crunch", new Recipe("Cinnamon ft. Cinnamon Toast Crunch", 
                new String[] {"flour", "corn starch", "baking powder", "salt", "cinnamon", "butter", "sugar", "brown sugar", "eggs", "vanilla"}, 2));
        
        recipeBook.put("Original ft. Lucky Charms", new Recipe("Original ft. Lucky Charms", 
                new String[] {"cheese", "brown sugar", "sugar", "lucky charms", "white chocolate chips", "eggs", "butter", "marshmallows", "flour", "salt", "baking soda", "vanilla"}, 2));
        
        recipeBook.put("Cookies and Cream Milkshake", new Recipe("Cookies and Cream Milkshake", 
                new String[] {"eggs", "vanilla", "butter", "sugar", "oreos", "flour", "corn starch", "baking powder", "salt"}, 2));
        
        recipeBook.put("Molten Lava", new Recipe("Molten Lava", 
                new String[] {"brown sugar", "vanilla", "eggs", "cocoa", "butter", "flour", "salt", "baking soda"}, 2));
        
        recipeBook.put("Cookie Dough", new Recipe("Cookie Dough", 
                new String[] {"brown sugar", "eggs", "vanilla", "flour", "salt", "baking powder", "corn starch", "butter", "milk chocolate chips", "ice cream"}, 2));
        
        recipeBook.put("Caramel Shortbread ft. Twix", new Recipe("Caramel Shortbread ft. Twix", 
                new String[] {"butter", "eggs", "vanilla", "milk chocolate chips", "brown sugar", "caramel", "flour", "corn starch", "salt", "baking powder"}, 2));
        
        recipeBook.put("Rocky Road", new Recipe("Rocky Road", 
                new String[] {"vanilla", "almonds", "cocoa", "eggs", "flour", "baking soda", "salt", "butter", "milk chocolate chips", "brown sugar"}, 2));
        
        recipeBook.put("Birthday Cake", new Recipe("Birthday Cake", 
                new String[] {"sugar", "eggs", "vanilla", "cake batter", "flour", "baking powder", "salt", "butter", "sprinkles"}, 1));
        
        recipeBook.put("Oatmeal Chocolate Chip", new Recipe("Oatmeal Chocolate Chip", 
                new String[] {"butter", "brown sugar", "sugar", "oats", "flour", "baking soda", "cinnamon", "salt", "milk chocolate chips", "eggs", "vanilla"}, 2));
        
        recipeBook.put("Dulce de Leche", new Recipe("Dulce de Leche", 
                new String[] {"sugar", "eggs", "vanilla", "flour", "baking powder", "cinnamon", "milk", "salt"}, 1));
        
        recipeBook.put("Milk Chocolate Chip", new Recipe("Milk Chocolate Chip", 
                new String[] {"butter", "sugar", "brown sugar", "eggs", "vanilla", "flour", "baking soda", "baking powder", "salt", "milk chocolate chip"}, 2));
        
        recipeBook.put("Semi-sweet Chocolate Chip", new Recipe("Semi-sweet Chocolate Chip", 
                new String[] {"butter", "brown sugar", "eggs", "vanilla", "baking soda", "salt", "corn starch", "flour", "semi-sweet chocolate chips"}, 2));
        
        recipeBook.put("Pink Sugar", new Recipe("Pink Sugar", 
                new String[] {"butter", "sugar", "eggs", "vanilla", "flour", "baking powder", "milk", "pink food coloring"}, 1));
    }

    // Method to display recipes
    public void displayRecipes() {
        for (String recipeName : recipeBook.keySet()) {
            System.out.println(recipeName);
        }
    }

    // Accessor
    public Recipe getRecipe(String recipeName) {
        return recipeBook.get(recipeName);
    }
}




