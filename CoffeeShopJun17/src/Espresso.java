
public class Espresso extends CoffeeDecorator {
    /**
     * Constructor
     * @param coffee
     */
    public Espresso(Coffee coffee) {
        super(coffee);
    }
    /**
     * addTopping constructor
     * @param coffee
     */
    @Override
    public void addTopping(Coffee coffee) {
        //coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }
    /**
     * Adds Espresso shot topping to order list
     * @return coffee + Espresso Shot
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + "-Espresso Shot";
    }
    //Adds Espresso shot cost of 0.35 to order
    /**
     * Adds Espresso Shot cost of 0.35 to order price
     * @return costs
     */
    @Override
    public Double cost() {
        return this.coffee.cost()+0.35;
    }
}
