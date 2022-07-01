/**
 *  Program that uses different classes, methods, files, and data types to create
 *  a custom coffee order then add it in a file to read for customer
 *  CS160L-01
 *  6/30/2022
 *  @author  Brandon Reynolds
 */
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.Date;

public class Main {
    /**
     * Main function that displays the initial menu for use and then utilizes a switch statement to execute the different options. Continues to create coffee order
     * until user exits out of program or inventory runs out.
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        ArrayList<String> Item = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> Temp = new ArrayList<>();
        int[] arrayInventory = new int[6];
        Stack<String> stack = new Stack<String>();
        int points = 0;
        String usePoints = "";
        Scanner CafeApplication = new Scanner(System.in);
            System.out.println("Cafe Application Running...");
            int input = 0;
            while (input != 1) {

                System.out.println("Press 1 : Read Inventory");
                System.out.println("Press 2 : Create Coffee Order");
                System.out.println("Press 3 : View customer's rewards account");
                System.out.println("Press 4 : Update Inventory");
                System.out.println("Press 5 : Update log file");
                System.out.println("Press 6 : Exit the application");

                switch(CafeApplication.nextLine()){
                    case "1":
                        //read from inventory.txt - Write new method inventorReader()
                        arrayInventory = inventorReader();
                        System.out.println("Current items in the inventory:");
                        System.out.println("Black Coffee = " + arrayInventory[0]);
                        System.out.println("Milk = " + arrayInventory[1]);
                        System.out.println("HotWater = " + arrayInventory[2]);
                        System.out.println("Espresso = " + arrayInventory[3]);
                        System.out.println("Sugar = " + arrayInventory[4]);
                        System.out.println("WhippedCream = " + arrayInventory[5]);
                        break;

                    case "2":
                        //Load in current inventory for orders
                        arrayInventory = inventorReader();
                        //Scanner for coffee size and to continue order
                        Scanner userOrders = new Scanner(System.in);
                        Scanner inputCup = new Scanner(System.in);
                        //Initial checker to verify there are black coffees to start order
                        if(arrayInventory[0] == 0){
                            System.out.println("Out of Coffee. Visit us later.");
                            input = 1;
                            break;
                        }

                        System.out.println("Coffee order created. Select toppings and size for the first coffee:");
                        String line = "yes";

                        do {
                            //Prompts user to enter customer coffee size due to different prices
                            System.out.println("What size coffee would they like: Large, Medium or Small? ");
                            double cupPrice = 0.0;
                            String sizeCup = inputCup.nextLine();

                            if(sizeCup.equals("Large") || sizeCup.equals("large")){
                                cupPrice = cupPrice + .50;
                            }
                            else if(sizeCup.equals("Medium") || sizeCup.equals("medium")){
                                cupPrice = 0.0;
                            }
                            else if(sizeCup.equals("Small") || sizeCup.equals("small")){
                                cupPrice = cupPrice - .50;
                            }
                            else{
                                System.out.println("Wrong size. Will put in new order as a medium");
                            }
                            //Call CreateOrder to return created coffee order into temporary Arraylist
                            Temp = CreateOrder(arrayInventory);
                            //add each string of coffee order that's printed to item ArrayList
                            for(int i = 0; i < Temp.size(); i = i + 2){
                                Item.add(sizeCup + " " + Temp.get(i));
                            }
                            //Add each converted string of double cost value to price Arraylist
                            for(int i = 1; i < Temp.size(); i = i + 2){
                                price.add(Double.parseDouble(Temp.get(i)) + cupPrice);
                            }
                            //Update black coffee inventory for each order
                            arrayInventory[0] = arrayInventory[0] - 1;
                            //Verify each order if there is still black coffee in inventory to continue, if not breaks.
                            if(arrayInventory[0] == 0){
                                System.out.println("Order Completed. No more coffees.");
                                break;
                            }
                            System.out.println("Do you want to add another coffee to this order? - yes or no");
                        } while (!(line = userOrders.nextLine()).equals("no"));

                        break;

                    case "3":
                        //Calls function to get customer's info and points then prompts the user to enter if they would like to use their points
                        Scanner scnr = new Scanner(System.in);
                        points = accountInfo();
                        System.out.println("Would the customer like to use their points?");
                        usePoints = scnr.nextLine();

                        break;

                    //Calls inventoryWriter to write updated inventory to inventory.txt
                    case "4":
                        inventoryWriter(arrayInventory);

                        break;
                    //calls PrintOrder to create customer receipt and then passes it to logWriter to write order to LogFile.txt
                    case "5":
                        stack.push(PrintOrder(Item, price, points, usePoints));
                        logWriter(stack);

                        break;
                    //Makes sure inventory.txt and LogFile.txt are updated before exiting program
                    case "6":
                        //Case 4
                        inventoryWriter(arrayInventory);
                        //Case 5
                        stack.push(PrintOrder(Item, price, points, usePoints));
                        logWriter(stack);

                        //Exit
                        input = 1;
                        break;
                    //Tells user invalid selection and prompts them to try again
                    default:
                        System.out.println("Invalid Selection. Please Try Again");
                }//End Switch

            }//End while

    }
    /**
     * Method passes in item and price ArrayList to generate a printed reciept using the created coffee order. Method also passes in
     * customer's points and user input if customer would like to apply points towards total
     * @param Item, price, points, usePoints
     * @return str (StringBuilder)
     */
    public static String PrintOrder(ArrayList<String> Item, ArrayList<Double> price, int points, String usePoints) {
        StringBuilder str = new StringBuilder();
        str.append("\nRECEIPT\n");
        //Create iterator to iterate through each ArrayList
        Iterator<String> nameIterator = Item.iterator();
        Iterator<Double> priceIterator = price.iterator();
        //Declare item number and initialize sum
        int i = 1;
        double sum = 0.0;
        //Loops through both iterators to print out items and price then calculates sum
        while (nameIterator.hasNext()) {
            double newprice = priceIterator.next();
            str.append("Item " + i + ": " + nameIterator.next() + " |Cost: " + newprice + "\n");
            sum += newprice;
            i++;
        }
        //Uses customer's points to make total cost of order go down if they choose to apply them
        if(usePoints.equals("Yes") || usePoints.equals("yes")){
            if(points > sum){
                sum = 0;
                points -= sum;//New points after using them towards transaction
            }
            else {
                sum = sum  - points;
                points = 0; //Points are 0
            }
            str.append("Points applied\n");
        }
        //Displays sum
        str.append("TOTAL COST OF ORDER: " + sum + "\n");
        //Displays total points earned from purchase
        str.append("You earned " + sum * 10 + " points today!");
        points += (int) (sum * 10); //Total new points after adding earned points

        return str.toString();
    }
    /**
     * Method passes in inventory array to utilize the quantity of toppings available to create coffee order. Method uses a switch statement to create
     * new object of basicCoffee with updated topping contents then returns the final contents. If a topping is out of stock then the switch statement
     * prompts user to select another topping until coffee order is complete.
     * @param inventoryArray
     * @return coffeeOrder
     */
    public static ArrayList<String> CreateOrder(int[] inventoryArray) {
        Scanner userFeedback = new Scanner(System.in);
        ArrayList<String> coffeeOrder = new ArrayList<String>();
        Coffee basicCoffee = new BlackCoffee(new BasicCoffee());
        int in = 0;
        while (in != 1) {
            System.out.println("Enter the following values to add toppings: 1.) milk, 2.) hot water, 3.) espresso, 4.) sugar, 5) whipped cream, e - to complete order");
            switch (userFeedback.nextLine()) {
                case "1":
                    if(inventoryArray[1] != 0){
                        basicCoffee = new Milk(basicCoffee);
                        inventoryArray[1] = inventoryArray[1] - 1;
                    }
                    else{
                        System.out.println("Out of milk. Try a different topping.");
                    }
                    break;

                case "2":
                    if(inventoryArray[2] != 0){
                        basicCoffee = new HotWater(basicCoffee);
                        inventoryArray[2] = inventoryArray[2] - 1;
                    }
                    else{
                        System.out.println("Out of hot water. Try a different topping.");
                    }
                    break;

                case "3":
                    if(inventoryArray[3] != 0){
                        basicCoffee = new Espresso(basicCoffee);
                        inventoryArray[3] = inventoryArray[3] - 1;
                    }
                    else{
                        System.out.println("Out of espresso. Try a different topping.");
                    }
                    break;

                case "4":
                    if(inventoryArray[4] != 0){
                        basicCoffee = new Sugar(basicCoffee);
                        inventoryArray[4] = inventoryArray[4] - 1;
                    }
                    else{
                        System.out.println("Out of sugar. Try a different topping.");
                    }
                    break;

                case "5":
                    if(inventoryArray[5] != 0){
                        basicCoffee = new WhippedCream(basicCoffee);
                        inventoryArray[5] = inventoryArray[5] - 1;
                    }
                    else{
                        System.out.println("Out of whipped cream. Try a different topping.");
                    }
                    break;

                case "e":
                    //Update whole order once when finished
                    coffeeOrder.add(basicCoffee.printCoffee());
                    coffeeOrder.add(String.valueOf(basicCoffee.cost()));
                    in = 1;
                    break;

                default:
                    System.out.println("Invalid Input");
            }
        }

            return coffeeOrder;
    }

    /**
     * Method that opens and reads inventory.txt to get inventory values. Uses a BufferedReader in order to traverse each line of inventory so that
     * it can return an updated inventory array. Uses a try catch IO exception to ensure the file is opened and closed properly.
     * @return inventoryArray
     */
    public static int[] inventorReader() {
        int[] inventoryArray = new int[6];
        int i = 0;
        int length = 0;
        BufferedReader reader = null;
        /*Use a try-catch block to create a string and then use it to hold each
        buffered line from inventory.txt. Next get length of line and convert
        integer character to int and store in array.
         */
        try{
            String currentLine;
            FileReader fileRead = new FileReader("inventory.txt");
            reader = new BufferedReader(fileRead);
            while((currentLine = reader.readLine()) != null) {
                length = currentLine.indexOf('=') + 2; //Get location of inventory value
                inventoryArray[i] = Integer.valueOf(length); //Get integer at the end of line and add it to array
                i++;
            }
        } catch(IOException e){
            System.out.println("ERROR: Could not read the file.");
        }
        //Use try-catch block to close file and print error if it doesn't work
        try {
            if (reader != null){
                reader.close();
            }
        } catch(IOException e2) {
            System.out.println("ERROR: Could not close file");
        }

        return inventoryArray;
    }

    /**
     * Method passes in inventory array to write over and update the previous contents of inventory.txt with updated inventoryArray. Uses a
     * FileWriter to open and close text file while using a try catch IO exception to ensure the file completes both procedures.
     * @param inventoryArray
     * @return none
     */
    public static void inventoryWriter(int[] inventoryArray){
        try{
            FileWriter writeInventory = new FileWriter("inventory.txt");

            writeInventory.write("Black Coffee = " + inventoryArray[0] + "\n");
            writeInventory.write("Milk = " + inventoryArray[1] + "\n");
            writeInventory.write("HotWater = " + inventoryArray[2] + "\n");
            writeInventory.write("Espresso = " + inventoryArray[3] + "\n");
            writeInventory.write("Sugar = " + inventoryArray[4] + "\n");
            writeInventory.write("WhippedCream = " + inventoryArray[5] + "\n");

            writeInventory.close();
            System.out.println("Successfully updated the inventory");
        } catch (IOException e){ //Will execute if exception is thrown from file
            System.out.println("LogFile.txt" + " could not be written to");
            System.exit(0);
        }
    }
    /**
     * Method passes in String stack that hold the contents from the generated order receipt and writes it to file LogFile.txt using a FileWriter.
     * Uses a try catch IO exception to ensure the file is opened and closed properly. Method appends the file by printing the current date and time
     * at the top of the receipt along with any stack contents. If the stack is empty, the current date and time still print but method displays
     * message to let user know that the stack is empty and there is nothing to log.
     * @param stack
     * @return void
     */
    public static void logWriter(Stack<String> stack){
        try{
        FileWriter myWriter = new FileWriter("LogFile.txt", true);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        myWriter.write("\n\nWriting orders from stack " + formatter.format(date));

        if(stack.isEmpty()){
            System.out.println("Nothing to log. Stack is empty");
        }

        while(stack.isEmpty() == false) {
            myWriter.append(stack.pop());
        }

        myWriter.close();
        System.out.println("Successfully updated the log file");

        } catch (IOException e){
            System.out.println("LogFile.txt" + " could not be written to");
            System.exit(0);
        }

    }
    /**
     * Method uses a FileReader to open and close MyAccount.txt file that contains customer's name and number of points. Uses a try catch IO exception to
     * display a message letting user know if file opened or closed unsuccessfully.
     * @return points
     */
    public static int accountInfo(){

        String findPoints;
        int points = 0;
        BufferedReader accountReader = null;
        /*Use a try-catch block to create a string and then use it to hold each
        buffered line from inventory.txt. Next gets customer name to display it and gets their point total to use
        towards transaction if desired
         */
        try{
            String accountLine;
            FileReader personalAccount = new FileReader("MyAccount.txt");
            accountReader = new BufferedReader(personalAccount);

            accountLine = accountReader.readLine(); //Read account holder name
            System.out.println("Ordering customer is " + accountLine + "!");
            accountLine = accountReader.readLine();

            findPoints = accountLine.substring(8); //Get location of points value
            System.out.println("Customer has " + findPoints + " points.");
            points = Integer.valueOf(findPoints); //Get integer value at the end of line and add it to points

        } catch(IOException e){
            System.out.println("ERROR: Could not read the file.");
        }
        //Use try-catch block to close file and print error if it doesn't work
        try {
            if (accountReader != null){
                accountReader.close();
            }
        } catch(IOException e2) {
            System.out.println("ERROR: Could not close file");
        }

        return points;
    }
}

