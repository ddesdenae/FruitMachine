package dddubrico;  

import javax.swing.JOptionPane;  

public class Main {  
    public static void main(String[] args) {  
        Register cashRegister = new Register(500);  // Storing $500 in cents  

        // Define juice options and prices (in dollars)  
        String[] juiceNames = {"Apple Juice", "Orange Juice", "Mango Lassi", "Fruit Punch"};  
        double[] juicePrices = {8.00, 10.00, 12.00, 15.00}; // prices in dollars  

        while (true) {  
            StringBuilder menu = new StringBuilder();  
            menu.append("========================================\n");  
            menu.append("          Fruit Juice Machine          \n");  
            menu.append("========================================\n");  
            menu.append(String.format("Current balance in cash register: $%.2f\n\n", cashRegister.getCurrentBalance()));  
            menu.append("Available Juices and Prices:\n");  
            for (int i = 0; i < juiceNames.length; i++) {  
                menu.append(String.format("%d. %s - $%.2f\n", (i + 1), juiceNames[i], juicePrices[i]));  
            }  
            menu.append("0. Exit");  

            // Show options and get user selection  
            String input = JOptionPane.showInputDialog(menu.toString());  
            int choice;  

            try {  
                choice = Integer.parseInt(input);  
                if (choice == 0) {  
                    JOptionPane.showMessageDialog(null, "Thank you for using the Fruit Juice Machine! Goodbye!");  
                    break; // Exit the loop and terminate the program  
                }  

                // Validate choice  
                if (choice < 1 || choice > juiceNames.length) {  
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");  
                    continue;  
                }  

                // Get the quantity of the selected juice  
                String quantityInput = JOptionPane.showInputDialog(String.format("How many %s would you like to order?", juiceNames[choice - 1]));  
                int quantity = Integer.parseInt(quantityInput);  

                double totalPrice = juicePrices[choice - 1] * quantity; // Calculate total price  
                String paymentPrompt = String.format("You selected %s. The total price is $%.2f. How would you like to pay? (1 for Dollars, 2 for Cents): ",  
                        juiceNames[choice - 1], totalPrice);  
                String paymentMethodInput = JOptionPane.showInputDialog(paymentPrompt);  
                int paymentMethod = Integer.parseInt(paymentMethodInput);  

                int totalInserted = 0; // Total cash inserted  
                boolean purchaseSuccess = false; // Track purchase success  

                // Allow up to 2 additional tries to input the correct amount  
                for (int tries = 0; tries < 3; tries++) {  
                    String insertedCashString;  
                    double insertedCash; // Store cash as double  
                    // Input for payment method  
                    if (paymentMethod == 1) { // Payment in dollars  
                        insertedCashString = JOptionPane.showInputDialog("Please insert cash in dollars: ");  
                        insertedCash = Double.parseDouble(insertedCashString);  
                    } else if (paymentMethod == 2) { // Payment in cents  
                        insertedCashString = JOptionPane.showInputDialog("Please insert cash in cents: ");  
                        insertedCash = Double.parseDouble(insertedCashString) / 100; // Convert cents to dollars  
                    } else {  
                        JOptionPane.showMessageDialog(null, "Invalid payment method selected.");  
                        break;  
                    }  

                    totalInserted += insertedCash; // Total amount inserted  
                    cashRegister.acceptAmount(insertedCash); // Accept amount in dollars  

                    // Check if enough cash was inserted  
                    if (totalInserted >= totalPrice) {  
                        double change = totalInserted - totalPrice; // Calculate change  
                        // Check if the register can give the change  
                        if (change > cashRegister.getCurrentBalance()) {  
                            JOptionPane.showMessageDialog(null, "Insufficient change in the register. Please input a smaller amount.");  
                            totalInserted = 0; // Reset total inserted for new input  
                        } else {  
                            cashRegister.makeSale(totalPrice); // Make sale  
                            String successMessage = String.format("========================================\n" +  
                                                "       Dispensing Your Juice!          \n" +  
                                                "========================================\n" +  
                                                "Thank you for your purchase of %d %s!\n", quantity, juiceNames[choice - 1]);  
                            if (change > 0) {  
                                successMessage += String.format("Your change: $%.2f", change);  
                            }  
                            JOptionPane.showMessageDialog(null, successMessage);  
                            purchaseSuccess = true;  
                            break;  
                        }  
                    } else {  
                        if (tries < 2) { // only ask for more cash on remaining attempts  
                            JOptionPane.showMessageDialog(null,  
                                    String.format("Insufficient funds. Please insert an additional $%.2f.",  
                                            (totalPrice - totalInserted)));  
                        }  
                    }  
                }  

                // If purchase was not successful after 3 tries  
                if (!purchaseSuccess) {  
                    JOptionPane.showMessageDialog(null,  
                            String.format("Purchase failed. Returning your total deposit of $%.2f.", totalInserted));  
                }  

                JOptionPane.showMessageDialog(null, "========================================");  

            } catch (NumberFormatException e) {  
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a number correctly.");  
            }  
        }  
    }  
}
