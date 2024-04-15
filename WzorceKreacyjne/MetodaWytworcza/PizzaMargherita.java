package WzorceKreacyjne.MetodaWytworcza;

import java.util.List;

public class PizzaMargherita implements Pizza{
    public List<String> ingredients;
    public boolean hasDoubleCheese;

    public PizzaMargherita(List<String> ingredients, boolean hasDoubleCheese) {
        super();

        this.ingredients = ingredients;
        this.hasDoubleCheese = hasDoubleCheese;
    }

    @Override
    public String deliver() {
        StringBuilder sb = new StringBuilder("Recipe:\n");
        for (String string : ingredients) {
            sb.append(string + "\n");
        }
        sb.append("has double cheese" + hasDoubleCheese + "\n");
        return sb.toString();
    }
    
}
