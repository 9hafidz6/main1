package duke.command.dishesCommand;

import duke.dish.Dish;
import duke.dish.DishList;
import duke.command.Cmd;
import duke.exception.DukeException;
import duke.list.GenericList;
import duke.storage.Storage;
import duke.ui.Ui;

public class InitCommand extends Cmd<Dish> {

    public InitCommand() {
        //clears all the amount in dishes
    }

    @Override
    public void execute(GenericList<Dish> dish1, Ui ui, Storage storage) throws DukeException {
        System.out.println("\t are you sure you want to clear list? (yes or no)");
        String command = ui.readCommand();
        if(command.equals("yes")){
            dish1.clearList();
            System.out.println("\t LIST IS CLEARED");
        }
        else if(command.equals("no")){
            System.out.println("\t LIST IS NOT CLEARED");
        }
    }
}
