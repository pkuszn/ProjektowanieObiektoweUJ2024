package WzorceKreacyjne.MetodaWytworcza;

import java.util.List;

public class PizzaHawai implements Pizza {
    public boolean hasDoubleAnanas;
    public boolean hasDoubleCheeese;
    public List<String> ingredients;
    public PizzaHawai(List<String> ingredients, boolean hasDoubleAnanas, boolean hasDoubleCheeese) {
        super();

        this.ingredients = ingredients;
        this.hasDoubleAnanas = hasDoubleAnanas;
        this.hasDoubleCheeese = hasDoubleAnanas;
    }

    @Override
    public String deliver() {
        StringBuilder sb = new StringBuilder("Recipe:\n");
        for (String string : ingredients) {
            sb.append(string + "\n");
        }
        sb.append("has double cheese" + hasDoubleCheeese + "\n");
        sb.append("has double ananas" + hasDoubleAnanas + "\n");
        return sb.toString();
    }
    
}
