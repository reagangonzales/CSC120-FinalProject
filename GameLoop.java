/* Manages game flow */
import java.util.Scanner;

public class GameLoop {

    public static void main(String[] args) {

        // This is a "flag" to let us know when the loop should end
        boolean stillPlaying = true;

        // We'll use this to get input from the user.
        Scanner userInput = new Scanner(System.in);

        // Storage for user's responses
        String userResponse = "";

        // This could be replaced with a more interesting opening
        System.out.println("******************");
        System.out.println("Welcome to The Crumbl Cookie Game");
        System.out.println("Objective: Bake and sell cookies!");
        System.out.println("******************");

        // Instructions are sometimes helpful
        System.out.println("\nEnter 'help' for commands or 'quit' to stop playing.\n");
        System.out.println("Find your keys and phone to leave the house!");

        // Set up the game world
        Player player = new Player("Chef");
        House house = new House("123 Baker Street", 1, 3);
        Grocery grocery = new Grocery("456 Market Lane", 1, 5);
        Crumbl crumbl = new Crumbl("789 Cookie Ave", 2, 4);
        RecipeBook recipeBook = new RecipeBook();
        Recipe currentRecipe = new Recipe(); // To hold the player's current blend
        
        // Start the player at the house
        player.setCurrentLocation(house);
        house.setPlayer(player); // Set the player in the house

        // Variables to track the player's position in the grocery store
        boolean inGroceryStore = false;
        String currentAisle = ""; // Tracks which aisle the player is in

        // Track state for Crumbl shop activities
        boolean inCrumblShop = false;
        String currentCrumbl = ""; // Tracks where in crumbl player is
        boolean hasBlended = false;
        boolean hasBaked = false;
        boolean hasDecorated = false;

        // The do...while structure means we execute the body of the loop once before checking the stopping condition
        do {
            // ************************************************
            // The stuff that happens in your game will go here
            //  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
            System.out.println("What would you like to do?");
            userResponse = userInput.nextLine().trim().toLowerCase();

            // Handle movement commands
            if (userResponse.startsWith("go ")) {
                String direction = userResponse.substring(3);
                switch (direction) {
                    case "east":
                        player.getCurrentLocation().goEast();
                        break;
                    case "west":
                        player.getCurrentLocation().goWest();
                        break;
                    case "north":
                        player.getCurrentLocation().goNorth();
                        break;
                    case "south":
                        player.getCurrentLocation().goSouth();
                        break;
                }
            }

            // Handle movement within the grocery store
            if (inGroceryStore && userResponse.startsWith("go ")) {
                String direction = userResponse.substring(3).trim();
                switch (direction) {
                    case "east":
                        currentAisle = "east";
                        System.out.println("You are in the baking soda/powder aisle");
                        break;
                    case "west":
                        currentAisle = "west";
                        System.out.println("You are in the baking essentials aisle.");
                        break;
                    case "north":
                        currentAisle = "north";
                        System.out.println("You are in the eggs and choco-peanut supplies aisle.");
                        break;
                    default:
                        System.out.println("You can't go that way in the grocery store.");
                }
            }

            // Handle movement within the Crumbl shop
            if (inCrumblShop && userResponse.startsWith("go ")) {
                String direction = userResponse.substring(3).trim();
                switch (direction) {
                    case "north":
                        currentCrumbl = "BlendDecorate";
                        System.out.println("You move north to the Blending & Decorating Area.");
                        break;
                    case "east":
                        currentCrumbl = "Front";
                        System.out.println("You move east to the Front Counter.");
                        break;
                    case "west":
                        currentCrumbl = "Oven";
                        System.out.println("You move west to the Oven.");
                        break;
                    default:
                        System.out.println("Invalid direction. Try north, east, or west.");
                        break;
                }
            }

            // Handle driving to destinations
            if (userResponse.startsWith("drive ")) {
                String direction = userResponse.substring(6);
                if (direction.equals("east")) {
                    System.out.println("You drive east and reach the Grocery Store.");
                    player.setCurrentLocation(grocery);
                } else if (direction.equals("west")) {
                    System.out.println("You drive west and reach the house.");
                    player.setCurrentLocation(house);
                } else if (direction.equals("north")) {
                    System.out.println("You drive north and reach the Crumbl Shop.");
                    player.setCurrentLocation(crumbl);
                } else {
                    System.out.println("Invalid direction.");
                }
            }

            switch (userResponse) {
                case "help":
                    player.getCurrentLocation().showOptions();
                    break;

                case "inventory":
                    player.getInventory().showInventory();
                    break;

                case "check progress":
                    System.out.println("You have made $" + player.getMoney() + " so far.");
                    System.out.println("You need $100 to win");
                    break;

                case "find keys":
                    house.findItem("keys");
                    break;

                case "find phone":
                    house.findItem("phone");
                    break;

                case "leave house":
                    if (player.getCurrentLocation() instanceof House) {
                        House currentHouse = (House) player.getCurrentLocation();
                        if (currentHouse.isReadyToLeave()) {
                            System.out.println("You leave the house and head to your car.");
                            Location car = new Location("Car", "Driveway", 1, 1);
                            player.setCurrentLocation(car);
                        } else {
                            System.out.println("You can't leave yet. Make sure you have everything you need!");
                        }
                    } else {
                        System.out.println("You are not in the house, so you can't use 'leave house' here.");
                    }
                    break;

                case "enter grocery store":
                    if (player.getCurrentLocation() == grocery) { // Check if the player is at the Grocery store
                        if (player.getInventory().hasItem("keys") && player.getInventory().hasItem("phone")) {
                            System.out.println("You enter the grocery store.");
                            System.out.println("Try to find as many ingredients as you can! Nina");
                            inGroceryStore = true;
                            currentAisle = "";
                        } else {
                            System.out.println("You need your keys and phone before you can enter the grocery store!");
                        }
                    } else {
                        System.out.println("You are not at the grocery store. You can't enter.");
                    }
                    break;

                case "find flour":
                case "find sugar":
                    if (inGroceryStore && currentAisle.equals("west")) {
                        String ingredient = userResponse.split(" ")[1];
                        grocery.findIngredient(ingredient);
                        player.getInventory().addItem(ingredient);
                    } else {
                        System.out.println("Ingredient not here.");
                    }
                    break;    

                case "find butter":
                case "find vanilla":
                    if (inGroceryStore && currentAisle.equals("west")) {
                        String ingredient = userResponse.split(" ")[1];
                        grocery.findIngredient(ingredient);
                        player.getInventory().addItem(ingredient);
                    } else {
                        System.out.println("Ingredient not here.");
                    }
                    break;

                case "find eggs":
                    if (inGroceryStore && currentAisle.equals("north")) {
                        String ingredient = userResponse.split(" ")[1];
                        grocery.findIngredient(ingredient);
                        player.getInventory().addItem(ingredient);
                    } else {
                        System.out.println("Ingredient not here.");
                    }
                    break;

                case "find baking powder":
                    if (inGroceryStore && currentAisle.equals("east")) { 
                        grocery.findIngredient("baking powder");
                        player.getInventory().addItem("baking powder");
                    } else {
                        System.out.println("Ingredient not here.");
                    }
                    break;
                
                case "find baking soda":
                    if (inGroceryStore && currentAisle.equals("east")) {
                        grocery.findIngredient("baking soda");
                        player.getInventory().addItem("baking soda");
                    } else {
                        System.out.println("Ingredient not here.");
                    }
                    break;

                case "find chocolate":
                    if (inGroceryStore && currentAisle.equals("north")) {
                        String ingredient = userResponse.split(" ")[1];
                        grocery.findIngredient(ingredient);
                        player.getInventory().addItem(ingredient);
                    } else {
                        System.out.println("Ingredient not here.");
                    }
                    break;

                case "find peanut butter":
                    if (inGroceryStore && currentAisle.equals("north")) { 
                        grocery.findIngredient("peanut butter");
                        player.getInventory().addItem("peanut butter");
                    } else {
                        System.out.println("Ingredient not here.");
                    }
                    break;

                case "leave grocery store":
                    if (inGroceryStore) {
                        boolean hasFlour = player.getInventory().hasItem("flour");
                        boolean hasSugar = player.getInventory().hasItem("sugar");
                        boolean hasEggs = player.getInventory().hasItem("eggs");
                        boolean hasBakingPowder = player.getInventory().hasItem("baking powder");
                        boolean hasBakingSoda = player.getInventory().hasItem("baking soda");
                        boolean hasChocolate = player.getInventory().hasItem("chocolate");
                        boolean hasPB = player.getInventory().hasItem("peanut butter");

                        if (hasFlour && hasSugar && hasEggs && hasBakingPowder && hasBakingSoda && hasChocolate && hasPB) {
                            System.out.println("You have all the ingredients. You leave the grocery store.");
                            inGroceryStore = false;
                        } else {
                            System.out.println("You leave the grocery store");
                            inGroceryStore = false;
                        }
                    } else {
                        System.out.println("You are not in the grocery store.");
                    }
                    break;

                case "enter crumbl":
                    if (player.getCurrentLocation() == crumbl) { // Check if the player is at Crumbl
                        if (player.getInventory().hasItem("flour") && player.getInventory().hasItem("sugar") && player.getInventory().hasItem("eggs")) {
                            System.out.println("Welcome to Crumbl! Bake a batch of cookies for the customers!");
                            inCrumblShop = true;
                        } else {
                            System.out.println("Welcome to Crumbl!");
                            inCrumblShop = true;
                        }
                    } else {
                        System.out.println("You are not at the Crumbl store. You can't enter.");
                        inCrumblShop = false;
                    }
                    break;

                case "blend":
                    if (currentCrumbl.equals("BlendDecorate")) {
                        System.out.println("Blending ingredients... What do you want to use? Type 'done' when finished.");
                        String ingredient;
                        while (true) {
                            ingredient = userInput.nextLine().trim().toLowerCase();
                            if (ingredient.equals("done")) break;
                            if (player.getInventory().hasItem(ingredient)) {
                                currentRecipe.addIngredient(ingredient);
                                player.getInventory().removeItem(ingredient);
                                System.out.println(ingredient + " added to the mix.");
                            } else {
                                System.out.println("You don't have " + ingredient + " in your inventory.");
                            }
                        }
                        hasBlended = true;
                        System.out.println("Blending complete!");
                    } else {
                        System.out.println("You are not at the blending station.");
                    }
                    break;
                
                case "bake cookie":      
                    if (hasBlended && currentCrumbl.equals("Oven")) {
                        crumbl.bakeCookie();
                        hasBaked = true;
                    } else if (!hasBlended) {
                        System.out.println("You need to blend ingredients first.");
                    } else if (!currentCrumbl.equals("Oven")) {
                        System.out.println("You are not at the oven.");
                    } else {
                        System.out.println("Can't do that");
                    }
                    break;
                
                case "decorate cookie":
                    if (hasBaked && currentCrumbl.equals("BlendDecorate")) {
                        crumbl.decorateCookie();
                        hasDecorated = true;
                    } else if (hasBaked) {
                        System.out.println("Cookie is ready to decorated but you are not at the decorate station");
                    } else if (currentCrumbl == "BlendDecorate") {
                        System.out.println("You do not have a cookie to decorate");
                    } else {
                        System.out.println("Can't do that");
                    }
                    break;
                
                case "sell cookie":
                    if (hasDecorated && currentCrumbl.equals("Front")) {
                        boolean madeCookie = recipeBook.checkRecipeMatch(currentRecipe);
                        if (madeCookie) {
                            System.out.println("Congratulations! You made a perfect cookie!");
                            player.addMoney(40); // Perfect match reward
                        } else {
                            int accuracy = recipeBook.calculateRecipeAccuracy(currentRecipe);
                            double deductionFactor = (100 - accuracy) * 0.5;  // 0.5 deduction per percent of accuracy lost
                            double earnings = 40 - deductionFactor;  // Deduct based on the accuracy
                            if (earnings < 0) earnings = 0;  // Ensure that earnings never go below 0
                            System.out.println("Your recipe accuracy is " + accuracy + "%.");
                            System.out.println("You earned $" + earnings);
                            player.addMoney(earnings);
                        }
                        hasBlended = false;
                        hasBaked = false;
                        hasDecorated = false;
                    } else if (hasDecorated) {
                        System.out.println("Can't sell cookie here.");
                    } else {
                        System.out.println("No cookie to sell.");
                    }
                    break;

                case "eat cookie":
                    if (hasBaked) {
                        System.out.println("You eat the cookie. It tastes delicious, but now you can't sell it..");
                        hasBlended = false;
                        hasBaked = false; // The cookie is eaten, so it is no longer available
                    } else if (hasBlended) {
                        System.out.println("You just ate a raw cookie! You died!");
                        stillPlaying = false;
                    }
                    break;
                

                case "leave crumbl":
                    if (inCrumblShop) {
                        System.out.println("You left crumbl and are now outside");
                        inCrumblShop = false;
                    } else {
                        System.out.println("You are not in the crumbl store.");
                    }
                    break;
            }
            // ***********************************************************************
            // And as the player interacts, you'll check to see if the game should end
            //  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
            if (player.getMoney() >= 100.0) {
                stillPlaying = false;
            }
            // Quit the game
            if (userResponse.equals("quit")) {
                stillPlaying = false;
            }
        } while (stillPlaying);

        // Tidy up
        userInput.close();

        // Once you exit the loop, you may need to deal with various possible stopping conditions
        if (userResponse.equals("quit")) {
            System.out.println("Goodbye");
        } else if (player.getMoney() >= 100.0) { 
            System.out.println("Congrats! You won the game!");
        } else {
            System.out.println("You lost! Try again");
        }
    }
}
