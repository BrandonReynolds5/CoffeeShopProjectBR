public interface Coffee {
    /**
     * addTopping constructor
     * @param coffee t
     */
    void addTopping(Coffee coffee);
    /**
     * Initialize printCoffee()
     */
    String printCoffee();
    /**
     * Initialize cost()
     */
    Double cost();
}
