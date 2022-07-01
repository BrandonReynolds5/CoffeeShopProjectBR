# CoffeeShopProjectBR
Coffee Shop program that utilizes files to create coffee orders and then writes them to a new file to display a receipt.


How to Run:
In order to run program, make sure all java files are downloaded along with all the txt files. 

How to contact me:
Email: bareynolds@sdsu.edu

File Descriptions:

Main.java - Main file that uses a switch statement to access different options for creating and updating coffee order. Option 1 calls the inventorReader() method to import all the lines that hold inventory from inventory.txt in order to update the created inventory array and returns it to be displayed in main. Next, option 2 creates a new coffee order by first getting the inventory just like in option 1, then if there is Black Coffee inventory will start by getting coffee size. Next, the option will call createOrder() to use another switch statement within a while loop to create new updated objects of the Coffee class for each topping while reducing the inventory until the loop is exited or inventory is done. Then option 3 imports the customer's account details with name and points. It will then ask if they would like to be used later. Option 4 updates the inventory after creating new orders by using the inventoryWriter method and writing back to inventory.txt. Option 5 then pushes the created coffee to printOrder to create a receipt which is written to LogFile.txt, and if points are used then discounts the transaction along with showing any new gained points. Lastly option 6 completes option 4 and 5 before exiting the program.

Coffee.java - Acts as an interface for other classes that utilize it in order to create new instances of Coffee build order. Initializes addTopping(), printOrder() and cost() methods for other classes to use.

BasicCoffee.java - file implements Coffee interface in order to act as the basis for all new coffee orders. Uses printOrder() method to return "Black Coffee" as the foundation for each coffee and uses cost() to return intial cost of coffee.

CoffeeDecorator.java - file implements Coffee interface in order to be used as the start for other topping classes. Creates a protected object of the Coffee Interface to use in its own methods of addTopping(), printOrder(), and cost() so that they can used to be built upon by other classes.

BlackCoffee.java - First class that extends CoffeeDecorator in order to overide the methods to return updated coffee order with any toppings added and costs. For this file adds Black Coffee which is already created by BasicCoffee class.

Expresso.java - Second class that extends CoffeeDecorator which overides the methods created by it to return the coffee order updated with "Espresso Shot" as the new topping and new cost with Espresso Shot cost.

HotWater.java - Third class that extends CoffeeDecorator which overides its methods to return Hot Water and updated price on top of the coffee price.

Milk.java - Fourth class that extends CoffeeDecorator which overides its methods to return coffee order with an updated topping of milk along with the new price after adding milk's cost.

Sugar.java - Fifth class that extends CoffeeDecorator which overides its methods to return coffee order with an updated topping of sugar along with the new price after adding sugar's cost.

WhippedCream.java - Sixth and final class that extends CoffeeDecorator to extends its methods. Returns the coffee order with an updated topping of whipped cream along with the new cost after adding whipped cream's cost.

inventory.txt - File that holds inventory files to be read or updated.

LogFile.txt - File that holds the newly created receipts from coffee orders.

MyAccount.txt - File that holds customer account with name and points.



