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
import duke.command.ingredientCommand.*;
import duke.command.orderCommand.*;
import duke.command.dishesCommand.*;
import duke.command.dishesCommand.InitCommand;

import duke.exception.DukeException;
import duke.command.orderCommand.*;
import duke.ingredient.Ingredient;

import duke.task.Deadline;
import duke.task.DoWithinPeriodTasks;
import duke.task.Event;
import duke.task.Todo;

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
    //public static Cmd parse(String fullCommand, int size) throws DukeException {
 /*   public static Cmd parse(String fullCommand, int size) throws DukeException {
>>>>>>> 07cc5da9aa7b9d4958918b61fa2d02c9fc22e062
        //splitted contains the keyword and the rest (description or task number)
        String[] splitted = fullCommand.split(" ", 2);
        //switching on the keyword
        switch (splitted[0]) {
            case "list":
                return new ListCommand();
            case "listtoday":
                return new FindToday();
            case "bye":
                return new ExitCommand();
            case "done":
                checkLength(splitted);
                return new DoneCommand(checkNumber(splitted[1], size));
            case "todo":
                checkLength(splitted);
                return new AddCommand(new Todo(splitted[1]));
            case "deadline":
                checkLength(splitted);
                String[] getBy = splitAndCheck(splitted[1], " /by ");
                return new AddCommand(new Deadline(getBy[0], getBy[1]));
            case "event":
                checkLength(splitted);
                String[] getAt = splitAndCheck(splitted[1], " /at ");
                return new AddCommand(new Event(getAt[0], getAt[1]));
            case "find":
                checkLength(splitted);
                return new FindIngredientCommand(splitted[1]);
            case "delete":
                checkLength(splitted);
                return new DeleteCommand(checkNumber(splitted[1], size));
            case "remind":
                return new RemindCommand();
            case "snooze":
                checkLength(splitted);
                String[] getUntil = splitAndCheck(splitted[1], " /until ");
                return new Snooze(checkNumber(getUntil[0], size), getUntil[1]);
            case "view":
                checkLength(splitted);
                Date splittedDate = Convert.stringToDate(splitted[1]);
                return new ViewCommand(splittedDate);
            case "period":
                checkLength(splitted);
                String[] getPart = splitAndCheck(splitted[1], " /from ");
                String[] part = splitAndCheck(getPart[1], " /to ");
                return new AddCommand(new DoWithinPeriodTasks(getPart[0], part[0], part[1]));
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
<<<<<<< HEAD
    /*
    public static Cmd parse(String fullCommand, int size) throws DukeException
    {
        //splitted contains the keyword and the rest (description or task number)
        String[] splitted = fullCommand.split(" ", 2);
        //switching on the keyword
        switch (splitted[0])
        {
            case "list":
                return new ListCommand();
            case "listtoday":
                return new FindToday();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
    */
    public static Cmd parse(String fullCommand, Duke.Type type) throws DukeException {
        String[] splitted;
        //= fullCommand.split(" ", 3);


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
                } else if (splitted[0].equals("listtoday")) {
                    if (splitted.length != 1)
                        throw new DukeException("follow the template: listtoday");
                    return new FindToday();
                } else if(splitted[0].equals("find")) {
                    if(splitted.length != 2)
                        throw new DukeException("follow the template: find <ingredient name>");
                    return new FindIngredientCommand(splitted[1]);
                }
                else
                    throw new DukeException("not a valid command for an Ingredient");

            }
            case DISH: {
                splitted = fullCommand.split(" ", 2);
                switch (splitted[0]) {
                    case "add":
                        if(splitted.length < 2) {
                            throw new DukeException("specify dish name");
                        }
                        else
                            splitted[1] = splitted[1].replaceAll("\\s+", " ");
                            return new AddDishCommand(new Dish(splitted[1]));
                    case "remove":
                        try {
                            return new DeleteDishCommand(Integer.parseInt(splitted[1]));
                        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                            throw new DukeException("enter a valid index");
                        }
                    case "list":
                        return new ListDishCommand();
                    case "initialize":
                        return new InitCommand();
                    case "ingredient":
                        String[] getIng = splitted[1].split(" ", 3);
                        int amount = 0;
                        int index = 0;
                        try {
                            amount = Integer.parseInt(getIng[1]);
                            index = Integer.parseInt(getIng[2]);
                        } catch (NumberFormatException e) {
                            throw new DukeException("enter a valid amount/index");
                        }
                        return new AddIngredient(new Ingredient(getIng[0], amount, new Date()), index);
                    default:
                        throw new DukeException("not a valid command for a Dish");
                }

            }
            case ORDER: {
                splitted = fullCommand.split(" ", 4);
                if (splitted.length > 4)
                    throw new DukeException("must specify order name and amount");
                else if (splitted[0].equals("add"))
                    //return new AddCommand<Order>(new Order(splitted[1]));
                if (splitted[0].equals("remove")) {
                    // for(int i=0)
                    return new DeleteCommand<Order>(Integer.parseInt(splitted[1]));
                } else
                    throw new DukeException("not a valid command for an Order");

            }
            default:
                throw new DukeException("not a valid type");
        }
    }

//     public static Cmd Parse(String fullCommand) throws DukeException {
//         //splitted contains the keyword and the rest (description or task number)
//         String[] splitted = fullCommand.split(" ", 2);
//         int orderNb;
//         //switching on the keyword
//         switch (splitted[0]) {
//             //RecipeCommand
//             case "dishadd":
//                 return new AddDishCommand(new Dish(splitted[1]));
//             case "dishlist":
//                 return new ListDishCommand();
//             case "dishdelete" :
//                 orderNb = Integer.parseInt(splitted[1]);
//                 return new DeleteDishCommand(orderNb);
//             case "dishingr" :
//                 String[] getIng = splitAndCheck(splitted[1], " /add ");
//                 int listNum = Integer.parseInt(getIng[1]);
//                 return new AddIngredient(new Ingredient(getIng[0], listNum, new Date()) , listNum);
//             case "dishinit" :
//                 return new InitCommand();
//             // OrderCommand
//             case "orderAdd":
//                 return new AddOrderCommand(new Order(), splitted[1]);
//             case "orderList":
//                 // splitted[1] can be orderList all, orderList undone,
//                 //                    orderList today, orderList undoneToday,
//                 //                    orderList date xxxx/xx/xx,
//                 //                    orderList dish dishname
//                 checkLength(splitted);
//                 return new ListOrderCommand(splitted[1]);
//             case "orderDone":
//                 checkLength(splitted);
//                 orderNb = Integer.parseInt(splitted[1]);
//                 return new DoneOrderCommand(orderNb);
//             case "orderDelete":
//                 orderNb = Integer.parseInt(splitted[1]);
//                 return new DeleteOrderCommand(orderNb);
//             case "orderAlterDate":
//                 checkLength(splitted);
//                 String[] getDate = splitAndCheck(splitted[1], " /to ");
//                 // getDate[0] is the order index, getDate[1] is the newly set date
//                 return new AlterDateCommand(Integer.parseInt(getDate[0]), getDate[1]);
//             default:
//                 throw new DukeException("I'm sorry, but I don't know what that means :-(");
//         }

//     }
//    public static Cmd parse(String fullCommand) throws DukeException {
//        //splitted contains the keyword and the rest (description or task number)
//        String[] splitted = fullCommand.split(" ", 2);
//        //switching on the keyword
//        switch (splitted[0]) {
//            //RecipeCommand
//            case "dishadd":
//                String[] getnum = splitAndCheck(splitted[1], " /num ");
//                int amount = Integer.parseInt(getnum[1]);
//                return new AddDishCommand(new Dish(getnum[0]), amount);
//            case "dishlist":
//                return new ListDishCommand();
//            case "dishdelete" :
//                int Nb = Integer.parseInt(splitted[1]);
//                return new DeleteDishCommand(Nb);
//            case "addingredient" :
//                String[] getIng = splitAndCheck(splitted[1], " /add ");
//                int listNum = Integer.parseInt(getIng[1]);
//                return new AddIngredient(new Ingredient(getIng[0], listNum, new Date()) , listNum);
//            case "dishinit" :
//                return new InitCommand();
//            // OrderCommand
//            case "orderAdd":
//                return new AddOrder();
//            case "orderList":
//                // splitted[1] can be orderList all, orderList undone,
//                //                    orderList today, orderList undoneToday
//                checkLength(splitted);
//                return new ListOrderCmd(splitted[1]);
//            case "orderDone":
//                checkLength(splitted);
//                return new DoneOrderCmd(splitted[1]);
//            case "orderCancel":
//                return new CancelOrderCmd(splitted[1]);
//            case "orderAlterDate":
//                checkLength(splitted);
//                String[] getDate = splitAndCheck(splitted[1], " /to ");
//                // getDate[0] is the order index, getDate[1] is the newly set date
//                return new AlterServingDateCmd(Integer.parseInt(getDate[0]), getDate[1]);
//            case "orderFindDate":
//                return new FindOrderByDate(splitted[1]);
//            default:
//                throw new DukeException("I'm sorry, but I don't know what that means :-(");
//        }
    //  }


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
