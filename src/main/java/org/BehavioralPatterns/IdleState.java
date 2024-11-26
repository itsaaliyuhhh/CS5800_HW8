package org.BehavioralPatterns;

public class IdleState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        Snack selectedSnack = vendingMachine.getSnacks().get(snackName);
        if (selectedSnack == null) {
            System.out.println("Snack not found.");
        } else if (selectedSnack.getQuantity() == 0) {
            System.out.println("Selected snack is out of stock.");
        } else {
            vendingMachine.setSelectedSnack(selectedSnack);
            System.out.println("Selected snack: " + snackName);
            vendingMachine.setState(new WaitingForMoneyState(vendingMachine));
        }
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Please select a snack first.");
    }

    @Override
    public void dispenseSnack() {
        System.out.println("No snack selected.");
    }
}
