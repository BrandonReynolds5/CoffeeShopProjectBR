public class Sugar extends CoffeeDecorator {
    /**
     * Constructor
     * @param coffee
     */
    public Sugar(Coffee coffee) {

        super(coffee);
    }
    /**
     * addTopping constructor
     * @param coffee
     */
    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }
    /**
     * Add sugar to order
     * @return coffee + sugar
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + "-sugar";
    }
    /**
     * Adds sugar cost of 0.05 to order
     * @return costs
     */
    @Override
    public Double cost() {
        return this.coffee.cost()+0.05;
    }
}
