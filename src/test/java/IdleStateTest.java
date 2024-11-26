import org.BehavioralPatterns.Snack;
import org.BehavioralPatterns.VendingMachine;
import org.BehavioralPatterns.WaitingForMoneyState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdleStateTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        Snack[] snacks = { new Snack("Coke", 2.50, 5) };
        vendingMachine = new VendingMachine(snacks);
    }

    @Test
    void testSelectSnackTransitionsToWaitingForMoneyState() {
        vendingMachine.selectSnack("Coke");
        assertTrue(vendingMachine.getCurrentState() instanceof WaitingForMoneyState);
    }

    @Test
    void testSelectSnackInvalidSnack() {
        assertDoesNotThrow(() -> vendingMachine.selectSnack("InvalidSnack"));
    }

    @Test
    void testInsertMoneyThrowsException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> vendingMachine.insertMoney(2.50));
        assertEquals("Please select a snack first.", exception.getMessage());
    }
}
