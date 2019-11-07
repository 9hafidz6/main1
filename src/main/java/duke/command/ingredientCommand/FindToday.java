package duke.command.ingredientCommand;

import duke.command.Command;
import duke.ingredient.Ingredient;
import duke.list.GenericList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a specific {@link Command} used to list Expired Ingredients occurring in the {@link Ingredient}.
 * @author x3chillax
 * Class FindToday is used to list all ingredients in the IngredientsList that are expired using 'listtoday'
 */

public class FindToday extends Command<Ingredient> {
    private Date today = new Date();
    private String pattern = "dd/MM/yyyy";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(GenericList<Ingredient> IngredientsList, Ui ui, Storage storage)
    {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : IngredientsList.getAllEntries())
        {     //for every ingredient, scan through the ingredientslist
            i += 1;
            if (ingredient.isExpiredToday(simpleDateFormat.format(ingredient.getExpiryDate())))
            {
                sb.append("\t ").append(i-1).append(". ").append(IngredientsList.getEntry(ingredient).toStringNoWarning()).append(".");
                sb.append(System.lineSeparator());
            }
        }
        if (sb.length() == 0) {
            System.out.println("No expired ingredients for today!");
        } else {
            System.out.println("\t Here are the expired ingredients for today");
            ui.showTask(sb.toString());
        }

    }
}

