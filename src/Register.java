package FruitMachine;

public class Register {  
    private int cashOnHand;  

    // Default constructor  
    public Register() {  
        this.cashOnHand = 500; // Initial cash in cents  
    }  

    // Constructor with parameter  
    public Register(int cashIn) {  
        this.cashOnHand = cashIn;  
    }  

    // Return the current amount in the cash register  
    public int getCurrentBalance() {  
        return cashOnHand;  
    }  

    // Receive the amount deposited by the customer  
    public void acceptAmount(int amountIn) {  
        cashOnHand += amountIn;  
    }  

    // Make a sale and update the cash register balance  
    public void makeSale(int cost) {  
        cashOnHand -= cost;  
    }  
}
