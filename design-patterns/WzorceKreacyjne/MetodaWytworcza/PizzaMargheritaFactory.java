package WzorceKreacyjne.MetodaWytworcza;

import java.util.ArrayList;
import java.util.List;

public class PizzaMargheritaFactory extends PizzaFactory {

    @Override
    PizzaMargherita createPizza() {
        List<String> ingredients = new ArrayList<String>() {
            {
                add("Tomato Sauce");
                add("Mozzarella Cheese");
                add("Fresh Basil");
            }
        };
        return new PizzaMargherita(ingredients, false);
    }
    
}
