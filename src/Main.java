package FruitMachine;
import java.util.InputMismatchException;  
import java.util.Scanner;  


public class Main {  
    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);  
        Register cashRegister = new Register();  

        // Define juices options and prices (in cents)  
        String[] juiceNames = {"Apple Juice", "Orange Juice", "Mango Lassi", "Fruit Punch"};  
        int[] juicePrices = {800, 1000, 1200, 1500}; // prices in cents  

        while (true) {  
            System.out.println("========================================");  
            System.out.println("          Fruit Juice Machine          ");  
            System.out.println("========================================");  

            // Display current balance in cash register  
            System.out.printf("Current balance in cash register: $%.2f%n", cashRegister.getCurrentBalance() / 100.0);  
            System.out.println();  

            // Display available juices  
            System.out.println("Available Juices and Prices (in dollars):");  
            for (int i = 0; i < juiceNames.length; i++) {  
                System.out.printf("%d. %s - $%.2f%n", (i + 1), juiceNames[i], juicePrices[i] / 100.0);  
            }  
            System.out.println("0. Exit");  

            // Ask user to select a juice  
            System.out.print("Please select a juice by entering the corresponding number (0-4): ");  
            int choice;  
            try {  
                choice = scanner.nextInt();  
                if (choice == 0) {  
                    System.out.println("Thank you for using the Fruit Juice Machine! Goodbye!");  
                    break; // Exit the loop and terminate the program  
                }  

                // Validate choice  
                if (choice < 1 || choice > juiceNames.length) {  
                    System.out.println("Invalid option. Please try again.");  
                    continue;  
                }  

                int selectedJuicePrice = juicePrices[choice - 1];  
                System.out.printf("You selected %s. The price is $%.2f. How would you like to pay? (1 for Dollars, 2 for Cents): ",   
                                  juiceNames[choice - 1], selectedJuicePrice / 100.0);  
                int paymentMethod = scanner.nextInt();  

                int totalInserted = 0;  
                boolean purchaseSuccess = false;  

                // Allow up to 2 additional tries to input the correct amount  
                for (int tries = 0; tries < 3; tries++) {  
                    int insertedCash;  

                    // Input for payment method  
                    if (paymentMethod == 1) {  
                        System.out.print("Please insert cash in dollars: ");  
                        insertedCash = (int) (scanner.nextDouble() * 100); // Convert dollars to cents  
                    } else if (paymentMethod == 2) {  
                        System.out.print("Please insert cash in cents: ");  
                        insertedCash = scanner.nextInt();  
                    } else {  
                        System.out.println("Invalid payment method selected. Please try again.");  
                        break;  
                    }  

                    totalInserted += insertedCash;  
                    cashRegister.acceptAmount(insertedCash);  
                    
                    if (totalInserted >= selectedJuicePrice) {  
                        cashRegister.makeSale(selectedJuicePrice);  
                        System.out.println("========================================");  
                        System.out.println("       Dispensing Your Juice!          ");  
                        System.out.println("========================================");  
                        System.out.println("Thank you for your purchase of " + juiceNames[choice - 1] + "!");  

                        // Calculate and return change if any  
                        int change = totalInserted - selectedJuicePrice;  
                        if (change > 0) {  
                            System.out.printf("Your change: $%.2f%n", change / 100.0);  
                        }  
                        purchaseSuccess = true;  
                        break;  
                    } else {  
                        if (tries < 2) { // only ask for more cash on remaining attempts  
                            System.out.printf("Insufficient funds. Please insert an additional $%.2f: ",   
                                              (selectedJuicePrice - totalInserted) / 100.0);  
                        }  
                    }  
                }  

                // If purchase was not successful after 3 tries  
                if (!purchaseSuccess) {  
                    System.out.printf("Purchase failed. Returning your total deposit of $%.2f.%n", totalInserted / 100.0);  
                }  

                System.out.println("========================================");  

            } catch (InputMismatchException e) {  
                System.out.println("Invalid option. Please enter a number.");  
                scanner.next(); // Clear the invalid input  
            }  
        }  

        scanner.close();  
    }  
}
