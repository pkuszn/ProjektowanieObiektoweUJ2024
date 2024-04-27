package WzorceKreacyjne.MetodaWytworcza;

import java.util.List;

public class PizzaCapricciosa implements Pizza {
    public List<String> ingredients;
    public boolean hasDoubleHam;

    public PizzaCapricciosa(List<String> ingredients, boolean hasDoubleHam) {
        super();
        this.ingredients = ingredients;
        this.hasDoubleHam = hasDoubleHam;
    }

    @Override
    public String deliver() {
        StringBuilder sb = new StringBuilder("Recipe:\n");
        for (String string : ingredients) {
            sb.append(string + "\n");
        }
        sb.append("has double ham" + hasDoubleHam + "\n");
        return sb.toString();
    }
}
