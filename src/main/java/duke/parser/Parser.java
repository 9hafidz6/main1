package duke.parser;

import duke.Duke;
import duke.command.Cmd;
import duke.command.ingredientCommand.AddCommand;
import duke.command.ingredientCommand.DeleteCommand;
import duke.command.ingredientCommand.UseCommand;
import duke.dish.Dish;
import duke.exception.DukeException;
import duke.ingredient.Ingredient;
import duke.order.Order;
import duke.command.dishesCommand.*;
import duke.command.orderCommand.*;
import duke.command.dishesCommand.InitCommand;

import java.util.Date;

/**
 * Represents a parser used to parse the input String from the user into a Duke understandable Command.
 * It should deals with making sense of the user command.
 */
public class Parser {

    //There is no constructor method for all others are static.

    /**
     * Returns a {@link Cmd} that can be understood by {@link Duke} and executed after.
     * We first split the fullCommand into 2, the keyword, followed by everything else.
     * Then we perform switching based on the keyword.
     *
     * @param fullCommand The String command entered by the user
     * @return Command The Command to be executed
     * @throws DukeException for any invalid input
     */
    public static Cmd parse(String fullCommand, Duke.Type type) throws DukeException {
        String[] splitted;

        switch (type) {
            case INGREDIENT: {
                splitted = fullCommand.split(" ");

                if (splitted[0].equals("add")) {
                    if (splitted.length != 4)
                        throw new DukeException("must specify ingredient name, amount and/or expiry date");
                    return new AddCommand(new Ingredient(splitted[1], Integer.parseInt(splitted[2]), splitted[3]));
                }
                if (splitted[0].equals("remove")) {
                    if (splitted.length != 2)
                        throw new DukeException("must specify a index");
                    return new DeleteCommand<Ingredient>(Integer.parseInt(splitted[1]));
                } else if (splitted[0].equals("use")) {
                    if (splitted.length != 3)
                        throw new DukeException("follow the template: use <ingredient name> <amount>");
                    return new UseCommand(new Ingredient(splitted[1], Integer.parseInt(splitted[2]), new Date()));
                } else
                    throw new DukeException("not a valid command for an Ingredient");

            }
            case DISH: {
                splitted = fullCommand.split(" ", 2);
                if (splitted.length > 4)
                    throw new DukeException("must specify name/index");
                else if (splitted[0].equals("add")) {
                    if(splitted.length != 2)
                        throw new DukeException("description cannot be empty");
                    return new AddDishCommand(new Dish(splitted[1]));
                }
                else if (splitted[0].equals("remove"))
                    try {
                        return new DeleteDishCommand(Integer.parseInt(splitted[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("enter a valid index");
                    }
                else if (splitted[0].equals("list"))
                    return new ListDishCommand();
                else if (splitted[0].equals("initialize"))
                    return new InitCommand();
                else if (splitted[0].equals("ingredient")) {
                    String[] getIng = splitted[1].split(" ", 3);
                    int amount = 0;
                    int index = 0;
                    try {
                        amount = Integer.parseInt(getIng[1]);
                        index = Integer.parseInt(getIng[2]);
                    } catch (NumberFormatException e) {
                        throw new DukeException("enter a valid amount/index");
                    }
                    return new AddIngredient(new Ingredient(getIng[0], amount, new Date()) , index);
                }
                else
                    throw new DukeException("not a valid command for a Dish");

            }
            case ORDER: {
                splitted = fullCommand.split(" ", 4);
                if (splitted.length > 4)
                    throw new DukeException("must specify order name, amount and expiry date");
                else if (splitted[0].equals("add"))
                    //return new AddCommand<Order>(new Order(splitted[1]));
                    throw new DukeException("TODO: Fix compile error.");
                if (splitted[0].equals("remove")) {
                    // for(int i=0)
                    return new DeleteCommand<Order>(Integer.parseInt(splitted[1]));
                } else
                    throw new DukeException("not a valid command for an Ingredient");

            }
            default:
                throw new DukeException("not a valid type");
        }
    }

    /**
     * Checks the length of a String array is of size 2.
     *
     * @throws DukeException when array is not of size 2.
     */
    public static Cmd order(String command) throws DukeException {
        String[] split = splitAndCheck(command, " ");
        switch (split[0]) {
            case "add":
                return new AddOrderCommand(new Order(), split[1]);
            case "list":
                //split[1] can be order list all, order list undone,
                //                order list today, order list undoneToday,
                //                order list date <xxxx/xx/xx>,
                //                order list dish <dishname>
                return new ListOrderCommand(split[1]);
            case "done":
                return new DoneOrderCommand(Integer.parseInt(split[1]));
            case "delete":
                return new DeleteOrderCommand(Integer.parseInt(split[1]));
            case "alterDate":
                //getDate[0] is the order index, getDate[1] is the newly set date
                //Example: order alterDate 2 /to 09/09/09
                String[] getDate = splitAndCheck(split[1], " /to ");
                return new AlterDateCommand(Integer.parseInt(getDate[0]), getDate[1]);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks the length of a String array is of size 2.
     *
     * @throws DukeException when array is not of size 2.
     */
    public static void checkLength(String[] str) throws DukeException {
        if (str.length != 2) {
            throw new DukeException("The description cannot be empty.");
        }
    }

    /**
     * Split a string and check its length.
     */
    public static String[] splitAndCheck(String str, String regex) throws DukeException {
        String[] part = str.split(regex, 2);
        checkLength(part); //Throws DukeException
        return part;
    }

    /**
     * Converts a string into a number, and checks if it is out of bounds.
     *
     * @return Returns a valid integer
     * @throws DukeException when it is invalid
     */
    public static int checkNumber(String str, int size) throws DukeException {
        int x;
        try {
            //Minus one because index starts from zero.
            //Throws NumberFormatException
            x = Integer.parseInt(str) - 1;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        if (x < 0 || x >= size) {
            //Index is out of bounds
            throw new DukeException("FUCK YOU JOEY!");
        }
        return x;
    }
}
