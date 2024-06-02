package WzorceKreacyjne.MetodaWytworcza;

public class FactoryMethodDesignPattern {
    public FactoryMethodDesignPattern() {
        super();
    }

    public static void main(String[] args) {
        PizzaFactory pizzaCapricciosaFactory = new PizzaCapricciosaFactory();
        PizzaFactory pizzaHawaiFactory = new PizzaHawaiFactory();
        PizzaFactory pizzaMarghertitaFactory = new PizzaMargheritaFactory();

        Pizza pizzaCapriciosa = pizzaCapricciosaFactory.createPizza();
        Pizza pizzaHawai = pizzaHawaiFactory.createPizza();
        Pizza pizzaMargherita = pizzaMarghertitaFactory.createPizza();

        System.out.println("The pizza capriciosa has been delivered\n" + pizzaCapriciosa.deliver());
        System.out.println("The pizza hawai has been delivered\n" + pizzaHawai.deliver());
        System.out.println("The pizza marghertita has been delivered\n" + pizzaMargherita.deliver());
    }
}