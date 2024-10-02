package FruitMachine;  

public class Register {  
    private double cashOnHand; // Cash on hand in dollars  

    // Default constructor  
    public Register() {  
        this.cashOnHand = 500; // Initial cash in dollars  
    }  

    // Constructor with parameter  
    public Register(double cashInDollars) {  
        this.cashOnHand = cashInDollars; // Store in dollars  
    }  

    // Return the current amount in the cash register in dollars  
    public double getCurrentBalance() {  
        return cashOnHand; // Return the balance directly in dollars  
    }  

    // Receive the amount deposited by the customer in dollars  
    public void acceptAmount(double amountInDollars) {  
        cashOnHand += amountInDollars; // Add the amount directly  
    }  

    // Make a sale and update the cash register balance  
    public void makeSale(double saleAmount) {  
        cashOnHand += saleAmount; // Update cash on hand with the sale amount  
    }  

    // Method to check if the register can give change  
    public boolean canGiveChange(double change) {  
        return change <= cashOnHand; // Check if the change can be given  
    }  
}
