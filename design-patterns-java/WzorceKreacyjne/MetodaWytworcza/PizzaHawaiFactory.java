package WzorceKreacyjne.MetodaWytworcza;

import java.util.ArrayList;
import java.util.List;

public class PizzaHawaiFactory extends PizzaFactory {

    @Override
    PizzaHawai createPizza() {
        List<String> ingredients = new ArrayList<String>() {
            {
                add("Tomato Sauce");
                add("Mozzarella Cheese");
                add("Ham");
                add("Pineapple");
            }
        };
        return new PizzaHawai(ingredients, false, false);
    }
}
