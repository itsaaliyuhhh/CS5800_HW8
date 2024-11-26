import org.BehavioralPatterns.ConcreteSnackHandler;
import org.BehavioralPatterns.Snack;
import org.BehavioralPatterns.SnackDispenseHandler;
import org.BehavioralPatterns.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnackDispenseHandlerTest {

    private SnackDispenseHandler handler;

    @BeforeEach
    void setUp() {
        Snack coke = new Snack("Coke", 2.50, 5);
        Snack pepsi = new Snack("Pepsi", 2.50, 0); // Out of stock
        Snack cheetos = new Snack("Cheetos", 1.75, 2);

        handler = new ConcreteSnackHandler(coke);
        SnackDispenseHandler pepsiHandler = new ConcreteSnackHandler(pepsi);
        SnackDispenseHandler cheetosHandler = new ConcreteSnackHandler(cheetos);

        handler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
    }

    @Test
    void testHandleRequestSuccess() {
        VendingMachine vendingMachine = new VendingMachine(new Snack[] {});
        assertDoesNotThrow(() -> handler.handleRequest("Coke", 2.50, vendingMachine));
    }

    @Test
    void testHandleRequestOutOfStock() {
        VendingMachine vendingMachine = new VendingMachine(new Snack[] {});
        assertThrows(RuntimeException.class, () -> handler.handleRequest("Pepsi", 2.50, vendingMachine));
    }

    @Test
    void testHandleRequestInvalidSnack() {
        VendingMachine vendingMachine = new VendingMachine(new Snack[] {});
        assertThrows(RuntimeException.class, () -> handler.handleRequest("InvalidSnack", 2.50, vendingMachine));
    }
}
