package org.BehavioralPatterns;

import java.util.HashMap;

public class VendingMachine {
    private StateOfVendingMachine currentState;
    private SnackDispenseHandler snackDispenserChain;
    private HashMap<String, Snack> snacks;
    private double moneyInserted;
    private Snack selectedSnack;

    public VendingMachine(Snack[] snackArray) {
        this.currentState = new IdleState(this);
        this.snacks = new HashMap<>();
        this.moneyInserted = 0;

        for (Snack snack : snackArray) {
            snacks.put(snack.getName(), snack);
        }

        initializeSnackChain(snackArray);
    }

    private void initializeSnackChain(Snack[] snackArray) {
        SnackDispenseHandler currentHandler = null;
        for (int i = snackArray.length - 1; i >= 0; i--) {
            SnackDispenseHandler handler = new ConcreteSnackHandler(snackArray[i]);
            if (currentHandler != null) {
                handler.setNextHandler(currentHandler);
            }
            currentHandler = handler;
        }
        this.snackDispenserChain = currentHandler;
    }

    public void selectSnack(String snackName) {
        currentState.selectSnack(snackName);
    }

    public void insertMoney(double amount) {
        currentState.insertMoney(amount);
    }

    public void dispenseSnack() {
        if (selectedSnack != null) {
            snackDispenserChain.handleRequest(selectedSnack.getName(), moneyInserted, this);
        } else {
            System.out.println("No snack selected.");
        }
        currentState.dispenseSnack();
    }

    public void dispenseChange(double amountToReturn) {
        if (amountToReturn > 0) {
            System.out.printf("Returning change: $%.2f%n", amountToReturn);
        }
        resetMoney();
    }

    public void setState(StateOfVendingMachine state) {
        this.currentState = state;
    }

    public StateOfVendingMachine getCurrentState() {
        return currentState;
    }

    public Snack getSelectedSnack() {
        return selectedSnack;
    }

    public void setSelectedSnack(Snack selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public double getMoneyInserted() {
        return moneyInserted;
    }

    public void addMoney(double amount) {
        this.moneyInserted += amount;
    }

    public void resetMoney() {
        this.moneyInserted = 0;
    }

    public HashMap<String, Snack> getSnacks() {
        return snacks;
    }
}
