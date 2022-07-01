public class BasicCoffee implements Coffee {
    /**
     * Constructor
     * @param coffee
     */
    @Override
    public void addTopping(Coffee coffee) {

    }
    /**
     * Prints Black Coffee for basic coffee
     * @return Black Coffee
     */
    @Override
    public String printCoffee() {
        return "Black coffee";
    }
    //Returns intitial price of 0.85
    /**
     * Returns intitial price of 0.85
     * @return cost
     */
    @Override
    public Double cost() {
        return 0.85;
    }
}
