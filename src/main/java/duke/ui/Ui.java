package duke.ui;

import duke.Duke;
import duke.ingredient.Ingredient;
import duke.ingredient.IngredientsList;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Represents the user interaction, used for getting the user input and printing the output on the screen.
 */
public class Ui {

    private Scanner scanner;
    private static final String line = "____________________________________________________________";

    /**
     * The constructor method for Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the input entered by the user.
     *
     * @return String the input entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Show a line.
     */
    public void showLine() {
        System.out.println("\t " + line);
    }

    /**
     * Used to print the greeting message from {@link Duke}.
     */
    public void showWelcome() {
        showLine();
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String greeting = "Hello";
        if (timeOfDay >= 0 && timeOfDay < 12) {
            greeting = "Good Morning";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            greeting = "Good Afternoon";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            greeting = "Good Evening";
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            greeting = "Good Night";
        }
        System.out.println("\t " + greeting + " chef! I'm Duke");

    }

    public void showHasExpiring() {
        System.out.println("\t A gentle reminder you have some  expired ingredients in the fridge");
        System.out.println("\t Would you like to see the list?");
    }

    public void showOptions() {
        System.out.println("Options (choose one): ");
        System.out.println("'a' remove all expiring");
        System.out.println("'b' add/remove/use an ingredient");
        System.out.println("'c' place/remove/change an order");
        System.out.println("'d' add/remove/change a dish");
        System.out.println("'q' to exit");
    }

    public void showUsed(Ingredient ingredient) {
        System.out.println("Great, just used " + ingredient);
    }

    public void show(String message) {
        System.out.println("\t " + message);
    }

    public void showIngredientTask() {
        showIngredientTemplate();
        System.out.println("type 'back' to go back to the main menu");
        System.out.println("type 'show' to see all ingredients currently in the fridge");
        System.out.println("type 'template' to see the format of the commands");
    }
public void showIngredientTemplate(){
    System.out.println("Continue by adding, removing or using an ingredient \nTemplate: ");
    showLine();
    System.out.println("add <Ingredient name> <amount> <expiry date: DD/MM/YYYY>");
    System.out.println("remove <ingredient number>");
    System.out.println("use <ingredient name> <amount> *always use most recently expiring ingredients first, to prevent food waste!*");
    showLine();
}
    public void showIngredientsInFridge(IngredientsList ingredientsList) {
        if (ingredientsList.isEmpty())
            System.out.println("The fridge is empty, better go buy some ingredients! ");
        else {
            System.out.println("Here is a list of all the ingredients in your fridge: ");
            int i = 1;
            for (Ingredient ingredient : ingredientsList.sortByExpiryDate().getAllEntries()) {
                System.out.println(i + ": " + ingredient);
                i++;
            }
        }
    }

    /**
     * Show loading error.
     */
    public void showLoadingError() {
        System.out.println("\t ☹ OOPS!!! Error while loading the list from the hard disc");
    }

    /**
     * Show the error to user.
     *
     * @param e an error
     */
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Show the task to user.
     *
     * @param task string
     */
    public void showTask(String task) {
        System.out.println(task);
    }

    /**
     * Show that this task is marked.
     *
     * @param doneTask The task that is marked as done
     */
    public void showMarkDone(String doneTask) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t " + doneTask);
    }

    /**
     * Show the task that has been snoozed.
     *
     * @param date        the date
     * @param changedTask the task that has been changed
     */
    public void showChangedDate(String date, String changedTask) {
        System.out.println("\t Nice! I've snoozed this task as until " + date + ":");
        System.out.println("\t " + changedTask);
    }

    /**
     * Show the order that has been changed serving date.
     *
     * @param date         the newly set date for serving the order
     * @param changedOrder the order that has been changed
     */
    public void showOrderChangedDate(String date, String changedOrder) {
        System.out.println("\t Nice! I've changed the order at " + date + ":");
        System.out.println("\t " + changedOrder);
    }

    /**
     * Show the size of the list.
     *
     * @param size the size
     */
    public void showSize(int size) {
        System.out.print("\t Now you have " + size);
        if (size == 1) {
            System.out.print(" task");
        } else {
            System.out.print(" tasks");
        }
        System.out.println(" in the list.");
    }

    /**
     * Show the size of the order list.
     *
     * @param size the size
     */
    public void showOrderListSize(int size) {
        System.out.print("\t Now you have " + size);
        if (size == 1) {
            System.out.print(" order");
        } else {
            System.out.print(" orders");
        }
        System.out.println(" in the order list.");
    }

    /**
     * Shows that a task has been added.
     *
     * @param command ay
     * @param size    ya
     */
    public void showAddCommand(String command, int size) {
        System.out.println("\t Got it. I've added this: ");
        System.out.println("\t " + command);
        // showSize(size);
    }

    /**
     * Shows that a order has been added.
     *
     * @param command ay
     * @param size    ya
     */
    public void showAddOrder(String command, int size) {
        System.out.println("\t Got it. I've added this order: ");
        System.out.println("\t " + command);
        showOrderListSize(size);
    }

    /**
     * Show the task that has been removed.
     *
     * @param removed the task
     * @param size    size of list
     */
    public void showRemovedTask(String removed, int size) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t " + removed);
        showSize(size);
    }

    /**
     * Show the order that has been removed.
     *
     * @param removed the order
     * @param size    size of order list
     */
    public void showRemovedOrder(String removed, int size) {
        System.out.println("\t Noted. I've removed this order:");
        System.out.println("\t " + removed);
        showOrderListSize(size);
    }

    public void showAddedDishes(String dish, int Nb) {
        System.out.println("\t you have added the following dish: ");
        System.out.println("\t " + dish + " \tamount: " + Nb);
    }

    public void showDishes(String Dish, int Nb) {
        System.out.println(Dish + "\t orders: " + Nb);
    }

    public void showDeletedDIsh(String dish) {
        System.out.println("\t The following dish have been removed:");
        System.out.println("\t " + dish);
    }
}
