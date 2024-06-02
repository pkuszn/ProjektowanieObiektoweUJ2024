package WzorceKreacyjne.MetodaWytworcza;

import java.util.ArrayList;
import java.util.List;

public class PizzaCapricciosaFactory extends PizzaFactory {

    @Override
    PizzaCapricciosa createPizza() {
        List<String> ingredients = new ArrayList<String>() {
            {
                add("Tomato Sauce");
                add("Mozzarella Cheese");
                add("Ham");
                add("Mushrooms");
                add("Artichokes");
            }
        };
        return new PizzaCapricciosa(ingredients, false);
    }
    
}
