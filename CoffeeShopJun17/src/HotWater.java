public class HotWater extends CoffeeDecorator {
    /**
     * Constructor
     * @param coffee
     */
    public HotWater(Coffee coffee) {
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
     * Returns hot water for order
     * @return Hot Water
     */
    @Override
    public String printCoffee() {
        return "Hot Water";
    }
    /**
     * Adds HotWater costs of 0.0 to order
     * @return costs
     */
    @Override
    public Double cost() {
        return 0.0;
    }
}
