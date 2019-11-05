package duke.dish;

import duke.ingredient.Ingredient;
import duke.ingredient.IngredientsList;
import duke.storage.Printable;

//@@ Author Hafidz
public class Dish implements Printable {

    private String dishname;
    private IngredientsList ingredientsList;

    /**
     * assigns dishname to name and instantiate ingredientList
     * @param name assigns dishname to name
     */
    public Dish(String name) {
        this.dishname = name;
        this.ingredientsList = new IngredientsList();
    }

    public int getIngredientSize() {
        return ingredientsList.size();
    }

    public Ingredient getIngredients(int index) {
        return ingredientsList.getEntry(index);
    }

    /**
     *
     * @return name of dish
     */
    public String getDishname() {
        return dishname;
    }

    /**
     * adding an ingredient to the ingredientList
     * @param ingredients to be added to list of ingredients
     */
    public void addIngredients(Ingredient ingredients) {
        ingredientsList.addEntry(ingredients);
    }

    /**
     * a loop to get all the ingredients in the list
     * @return all ingredients associated to the dish in string format
     */
    public String toString() {
        String str = "";
        for (Ingredient i : ingredientsList.getAllEntries()) {
            str += i.getName() + ",";
        }
        return str;
    }

    @Override
    public String printInFile() {
        return null;
    }
}
