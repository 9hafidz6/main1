package duke.command.orderCommand;

import duke.command.Cmd;
import duke.exception.DukeException;
import duke.list.GenericList;
import duke.order.Order;
import duke.storage.Storage;
import duke.ui.Ui;

public class InitOrderListCommand extends Cmd<Order> {

    public InitOrderListCommand() {
    }

    @Override
    public void execute(GenericList<Order> orderList, Ui ui, Storage orderStorage) throws DukeException {
        System.out.println("\t Are you sure you want to clear all orders in the order list? [y/n]");
        String command = ui.readCommand();
        if(command.toLowerCase().equals("y")){
            ui.showLine();
            orderList.clearList();
            orderStorage.clearInfoForFile();
            System.out.println("\t ORDER LIST CLEARED");
            System.out.println("\n\t Continue by adding order. Template:");
            System.out.println("\t add [-d ORDER_DATE-(dd/mm/yyyy)] -n DISH1_NAME[*DISH_AMOUNT], DISH2_NAME[*DISH_AMOUNT]");
            ui.showLine();
        } else if(command.toLowerCase().equals("n")){
            ui.showLine();
            System.out.println("\t ORDER LIST NOT CLEARED");
            System.out.println("\n\t Continue by adding, removing, altering, listing order.");
            System.out.println("\t Type 'template' to see the format of the commands");
            ui.showLine();
        } else { throw new DukeException("Please enter y or n after 'init' command"); }

    }

}
