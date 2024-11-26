package org.BehavioralPatterns;

public class DispensingState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Dispensing in progress. Please wait.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Dispensing in progress. Cannot insert money now.");
    }

    @Override
    public void dispenseSnack() {
        Snack snack = vendingMachine.getSelectedSnack();
        if (snack.getQuantity() > 0) {
            snack.setQuantity(snack.getQuantity() - 1);
            System.out.println("Dispensing: " + snack.getName());
            double change = vendingMachine.getMoneyInserted() - snack.getPrice();
            vendingMachine.dispenseChange(change); // Pass the change as an argument
        } else {
            System.out.println("Out of stock. Returning your money.");
            vendingMachine.resetMoney();
        }
        vendingMachine.setState(new IdleState(vendingMachine));
    }
}
