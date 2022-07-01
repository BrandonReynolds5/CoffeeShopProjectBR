
public class WhippedCream extends CoffeeDecorator {
    /**
     * Constructor
     * @param coffee
     */
    public WhippedCream(Coffee coffee) {
        super(coffee);
    }
    /**
     * addTopping constructor
     * @param coffee
     * @return coffee
     */
    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }
    /**
     * Add whipped cream to order
     * @return coffee + whipped cream
     */
    @Override
    public String printCoffee() {
        if (this.coffee instanceof WhippedCream) {
            return "1";
        } else {
            return this.coffee.printCoffee() + "-whipped cream";
        }
    }

    /**
     * Adds whipped cream cost of 0.10 to order
     * @return costs
     */
    @Override
    public Double cost() {
        if (this.coffee instanceof WhippedCream) {
            return 1.0;
        }
        else{
            return this.coffee.cost() + 0.10;
        }
    }
}
