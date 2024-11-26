package org.BehavioralPatterns;

public abstract class SnackDispenseHandler {
    protected SnackDispenseHandler nextHandler;

    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String snackName, double moneyInserted, VendingMachine vendingMachine);
}
