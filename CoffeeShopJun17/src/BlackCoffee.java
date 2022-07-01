public class BlackCoffee extends CoffeeDecorator {
    /**
     * Constructor
     * @param coffee
     */
    public BlackCoffee(Coffee coffee) {
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
       // instructions();
    }
    /**
     * Returns prinCoffee for order
     * @return printCoffee()
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee();
    }
    /**
     * Returns cost for order
     * @return cost
     */
    @Override
    public Double cost() {
        return this.coffee.cost();
    }
    /**
     * Prints instructions for coffee
     */
    public void instructions() {
        System.out.println("Pour coffee from pot into cup");
    }
}
