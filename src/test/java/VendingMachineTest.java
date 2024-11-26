import org.BehavioralPatterns.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        Snack[] snacks = {
                new Snack("Coke", 2.50, 5),
                new Snack("Pepsi", 2.50, 3),
                new Snack("Cheetos", 1.75, 2),
                new Snack("Doritos", 1.75, 0),
                new Snack("KitKat", 1.50, 2),
                new Snack("Snickers", 1.75, 0)
        };
        vendingMachine = new VendingMachine(snacks);
    }

    @Test
    void testSelectSnackAndInsertExactMoney() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.50);
        assertDoesNotThrow(vendingMachine::dispenseSnack);
    }

    @Test
    void testSelectSnackAndInsertInsufficientMoney() {
        vendingMachine.selectSnack("Pepsi");
        vendingMachine.insertMoney(1.00);

        assertThrows(RuntimeException.class, vendingMachine::dispenseSnack);
    }

    @Test
    void testOutOfStockSnack() {
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(1.75);

        assertThrows(RuntimeException.class, vendingMachine::dispenseSnack);
    }

    @Test
    void testOverpayAndReceiveChange() {
        vendingMachine.selectSnack("Cheetos");
        vendingMachine.insertMoney(2.00);

        assertDoesNotThrow(() -> {
            vendingMachine.dispenseSnack();
            double expectedChange = 0.25;
            assertEquals(expectedChange, vendingMachine.getMoneyInserted(), 0.01);
        });
    }

    @Test
    void testInvalidSnackSelection() {
        vendingMachine.selectSnack("InvalidSnack");

        assertThrows(RuntimeException.class, vendingMachine::dispenseSnack);
    }

    @Test
    void testMultipleDispensesAndStockDepletion() {
        vendingMachine.selectSnack("KitKat");
        vendingMachine.insertMoney(1.50);
        assertDoesNotThrow(vendingMachine::dispenseSnack); // First dispense

        vendingMachine.selectSnack("KitKat");
        vendingMachine.insertMoney(1.50);
        assertDoesNotThrow(vendingMachine::dispenseSnack); // Second dispense

        vendingMachine.selectSnack("KitKat");
        vendingMachine.insertMoney(1.50);
        assertThrows(RuntimeException.class, vendingMachine::dispenseSnack);
    }

    @Test
    void testStateTransitionIdleToWaitingForMoney() {
        assertTrue(vendingMachine.getCurrentState() instanceof IdleState);

        vendingMachine.selectSnack("Coke");
        assertTrue(vendingMachine.getCurrentState() instanceof WaitingForMoneyState);
    }

    @Test
    void testStateTransitionToDispensing() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.50);

        assertTrue(vendingMachine.getCurrentState() instanceof DispensingState);
    }
}
