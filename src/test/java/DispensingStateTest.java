import org.BehavioralPatterns.Snack;
import org.BehavioralPatterns.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispensingStateTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        Snack[] snacks = { new Snack("Coke", 2.50, 1) };
        vendingMachine = new VendingMachine(snacks);
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.50);
    }

    @Test
    void testDispenseSnackReducesQuantity() {
        vendingMachine.dispenseSnack();
        assertEquals(0, vendingMachine.getSnacks().get("Coke").getQuantity());
    }

    @Test
    void testDispenseSnackOutOfStock() {
        vendingMachine.dispenseSnack();
        assertThrows(RuntimeException.class, vendingMachine::dispenseSnack);
    }

    @Test
    void testDispenseSnackReturnsChange() {
        vendingMachine.insertMoney(3.00); // Overpay
        vendingMachine.dispenseSnack();
        assertEquals(0.50, vendingMachine.getMoneyInserted(), 0.01);
    }
}
