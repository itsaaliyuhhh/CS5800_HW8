package org.BehavioralPatterns;

public class VendingMachineDriver {
    public static void main(String[] args) {
        Snack[] snacks = {
                new Snack("Coke", 2.50, 5),
                new Snack("Pepsi", 2.50, 3),
                new Snack("Cheetos", 1.75, 2),
                new Snack("Doritos", 1.75, 0),
                new Snack("KitKat", 1.50, 2),
                new Snack("Snickers", 1.75, 0)
        };

        VendingMachine vendingMachine = new VendingMachine(snacks);

        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(1.75);
        vendingMachine.dispenseSnack();

        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(3.00);
        vendingMachine.dispenseSnack();

        vendingMachine.selectSnack("Pepsi");
        vendingMachine.insertMoney(2.50);
        vendingMachine.dispenseSnack();

        vendingMachine.selectSnack("Cheetos");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();

        vendingMachine.selectSnack("KitKat");
        vendingMachine.insertMoney(1.50);
        vendingMachine.dispenseSnack();
    }
}
