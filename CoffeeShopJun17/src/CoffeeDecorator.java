public abstract class CoffeeDecorator implements Coffee {
    //Initialize coffee variable
    protected Coffee coffee;
    /**
     * Constructor
     * @param coffee t
     */
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    /**
     * addTopping constructor
     * @param coffee t
     */
    public void addTopping(Coffee coffee) {
        this.coffee.addTopping(coffee);
    }
    /**
     * Returns printCoffee order
     * @return printCoffee
     */
    public String printCoffee() {
        return this.coffee.printCoffee();
    }
    /**
     * Initialize cost()
     */
    public abstract Double cost();
}
