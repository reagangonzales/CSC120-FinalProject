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
        System.out.println("Enter 'help' for commands or 'quit' to stop playing.\n");

        // Set up the game world
        Player player = new Player("Chef");
        House house = new House("123 Baker Street", 1, 3);
        Grocery grocery = new Grocery("456 Market Lane", 1, 5);
        Crumbl crumbl = new Crumbl("789 Cookie Ave", 2, 4);
        
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

        int eggs = 0;

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
                if (direction.equals("east")) {
                    currentAisle = "east";
                    System.out.println("You are in the flour aisle.");
                } else if (direction.equals("west")) {
                    currentAisle = "west";
                    System.out.println("You are in the sugar aisle.");
                } else if (direction.equals("north")) {
                    currentAisle = "north";
                    System.out.println("You are in the egg aisle.");
                } else {
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
                    System.out.println("You drive west and reach the Crumbl Shop.");
                    player.setCurrentLocation(crumbl);
                } else if (direction.equals("north")) {
                    System.out.println("You drive north and reach the house.");
                    player.setCurrentLocation(house);
                } else {
                    System.out.println("Invalid direction.");
                }
            }

            // ***********************************************************************
            // And as the player interacts, you'll check to see if the game should end
            //  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
            if (player.getMoney() >= 100) {
                stillPlaying = false;
            }
            // Quit the game
            if (userResponse.equals("quit")) {
                stillPlaying = false;
            }

            switch (userResponse) {
                case "help":
                    player.getCurrentLocation().showOptions();
                    break;

                case "enter":
                    player.getCurrentLocation().enter();
                    break;

                case "exit":
                    player.setCurrentLocation(player.getCurrentLocation().exit());
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
                    if (inGroceryStore && currentAisle.equals("east")) {
                        grocery.findIngredient("flour");
                        player.getInventory().addItem("flour");
                    } else {
                        System.out.println("You need to be in the flour aisle to find flour.");
                    }
                    break;

                case "find sugar":
                    if (inGroceryStore && currentAisle.equals("west")) {
                        grocery.findIngredient("sugar");
                        player.getInventory().addItem("sugar");
                    } else {
                        System.out.println("You need to be in the sugar aisle to find sugar.");
                    }
                    break;

                case "find eggs":
                    if (inGroceryStore && currentAisle.equals("north")) {
                        grocery.findIngredient("eggs");
                        player.getInventory().addItem("eggs");
                    } else {
                        System.out.println("You need to be in the egg aisle to find eggs.");
                    }
                    break;

                case "leave grocery store":
                    if (inGroceryStore) {
                        boolean hasFlour = player.getInventory().hasItem("flour");
                        boolean hasSugar = player.getInventory().hasItem("sugar");
                        boolean hasEggs = player.getInventory().hasItem("eggs");

                        if (hasFlour && hasSugar && hasEggs) {
                            System.out.println("You have all the ingredients. You leave the grocery store.");
                            inGroceryStore = false;
                        } else {
                            System.out.println("You can't leave yet. Make sure you have flour, sugar, and eggs!");
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
                            System.out.println("You can't bake without all the ingredients!");
                        }
                    } else {
                        System.out.println("You are not at the Crumbl store. You can't enter.");
                    }
                    break;

                case "blend":
                    if (currentCrumbl == "BlendDecorate") {
                        System.out.println("How many eggs will you use?");
                        eggs = Integer.parseInt(userInput.nextLine().trim());
                        crumbl.useBlender(eggs, player);
                        hasBlended = true;
                    } else {
                        System.out.println("Not at the blend station");
                    }
                    break;
                
                case "bake cookie":
                    if (hasBlended && currentCrumbl == "Oven") {
                        crumbl.bakeCookie();
                        hasBaked = true;
                    } else if (hasBlended) {
                        System.out.println("You have blended your ingredients but you are not at the oven");
                    } else if (currentCrumbl == "Oven") {
                        System.out.println("You do not have ingredients to blend");
                    } else {
                        System.out.println("Can't do that");
                    }
                    break;
                
                case "decorate cookie":
                    if (hasBaked && currentCrumbl == "BlendDecorate") {
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
                    if (hasDecorated && currentCrumbl == "Front") {
                        crumbl.sellCookie(eggs, player);
                    } else if (hasDecorated) {
                        System.out.println("You're cookie is ready to sell but you are not at the front counter");
                    } else if (currentCrumbl == "Front") {
                        System.out.println("You do not have a cookie to sell. If you baked a cookie, have you decorated it?");
                    } else {
                        System.out.println("Can't do that");
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
        } while (stillPlaying);

        // Tidy up
        userInput.close();

        // Once you exit the loop, you may need to deal with various possible stopping conditions
        if (userResponse.equals("quit")) {
            System.out.println("Goodbye");
        } else { 
            System.out.println("Congrats! You won the game!");
        }
    }
}