package org.BehavioralPatterns;

public class WaitingForMoneyState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public WaitingForMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Snack already selected. Insert money to proceed.");
    }

    @Override
    public void insertMoney(double amount) {
        vendingMachine.addMoney(amount);
        System.out.printf("Inserted: $%.2f | Total: $%.2f%n", amount, vendingMachine.getMoneyInserted());

        if (vendingMachine.getMoneyInserted() >= vendingMachine.getSelectedSnack().getPrice()) {
            vendingMachine.setState(new DispensingState(vendingMachine));
            vendingMachine.dispenseSnack();
        }
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Please insert enough money to purchase the snack.");
    }
}
