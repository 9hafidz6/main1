package duke.command.dishesCommand;

import duke.command.Command;
import duke.dish.Dish;
import duke.exception.DukeException;
import duke.list.GenericList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.function.DoubleUnaryOperator;


public class DeleteDishCommand extends Command<Dish> {

    private int Nb;

    public DeleteDishCommand(int dishNb) {
        //super(dishNb);
        this.Nb = dishNb;
    }

    @Override
    public void execute(GenericList<Dish> dish1, Ui ui, Storage storage) throws DukeException {
        try {
            ui.showDeletedDIsh(dish1.getEntry(Nb - 1).getDishname());
            dish1.removeEntry(Nb - 1);
        } catch (Exception e) {
            throw new DukeException("dish does not exist");
        }
    }
}
