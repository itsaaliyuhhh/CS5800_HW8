package org.BehavioralPatterns;

public class ConcreteSnackHandler extends SnackDispenseHandler {
    private Snack snack;

    public ConcreteSnackHandler(Snack snack) {
        this.snack = snack;
    }

    @Override
    public void handleRequest(String snackName, double moneyInserted, VendingMachine vendingMachine) {
        if (snack.getName().equalsIgnoreCase(snackName)) {
            if (snack.getQuantity() == 0) {
                System.out.println(snackName + " is out of stock.");
                vendingMachine.resetMoney();
            } else if (moneyInserted >= snack.getPrice()) {
                snack.setQuantity(snack.getQuantity() - 1);
                System.out.println("Dispensing: " + snackName);
                double change = moneyInserted - snack.getPrice();
                vendingMachine.dispenseChange(change);
            } else {
                System.out.printf("Not enough money for %s. Price: $%.2f | Inserted: $%.2f%n", snackName, snack.getPrice(), moneyInserted);
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName, moneyInserted, vendingMachine);
        } else {
            System.out.println("Snack not found.");
            vendingMachine.resetMoney();
        }
    }
}
