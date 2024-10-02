package dddubrico;  

public class Register {  
    private int cashOnHand; // Cash on hand in cents  

    // Default constructor  
    public Register() {  
        this.cashOnHand = 50000; // Initial cash in cents, represents $500  
    }  

    // Constructor with parameter  
    public Register(int cashInDollars) {  
        this.cashOnHand = cashInDollars * 100; // Store in cents  
    }  

    // Return the current amount in the cash register in dollars  
    public double getCurrentBalance() {  
        return cashOnHand / 100.0; // Convert cents to dollars for return  
    }  

    // Receive the amount deposited by the customer in cents  
    public void acceptAmount(double amountInDollars) {  
        cashOnHand += amountInDollars * 100; // Convert dollars to cents and add  
    }  

    // Make a sale and update the cash register balance  
    public void makeSale(double saleAmount) {  
        cashOnHand += saleAmount * 100; // Update cash on hand with the sale amount  
    }  

    // Method to check if the register can give change  
    public boolean canGiveChange(double change) {  
        return change <= getCurrentBalance();  
    }  
}
