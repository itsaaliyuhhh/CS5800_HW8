import org.BehavioralPatterns.DispensingState;
import org.BehavioralPatterns.Snack;
import org.BehavioralPatterns.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitingForMoneyStateTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        Snack[] snacks = { new Snack("Coke", 2.50, 5) };
        vendingMachine = new VendingMachine(snacks);
        vendingMachine.selectSnack("Coke");
    }

    @Test
    void testInsertMoneyTransitionsToDispensingState() {
        vendingMachine.insertMoney(2.50);
        assertTrue(vendingMachine.getCurrentState() instanceof DispensingState);
    }

    @Test
    void testInsertInsufficientMoney() {
        vendingMachine.insertMoney(1.00);
        assertFalse(vendingMachine.getCurrentState() instanceof DispensingState);
    }
}
