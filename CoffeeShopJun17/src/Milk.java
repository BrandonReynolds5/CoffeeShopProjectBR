public class Milk extends CoffeeDecorator {
    /**
     * Constructor
     * @param coffee
     */
    public Milk(Coffee coffee) {
        super(coffee);
    }
    /**
     * addTopping constructor
     * @param coffee
     */
    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = this.coffee;
    }

    /**
     * Adds milk topping to order list
     * @return coffee + milk
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + "-milk";
    }
    /**
     * Adds Milk cost of 0.15 to order price
     * @return costs
     */
    @Override
    public Double cost() {
        return this.coffee.cost()+0.15;
    }
}
