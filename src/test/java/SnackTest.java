import org.BehavioralPatterns.Snack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnackTest {

    @Test
    void testSnackCreation() {
        Snack snack = new Snack("Coke", 2.50, 5);
        assertEquals("Coke", snack.getName());
        assertEquals(2.50, snack.getPrice());
        assertEquals(5, snack.getQuantity());
    }

    @Test
    void testSettersAndGetters() {
        Snack snack = new Snack("Coke", 2.50, 5);
        snack.setName("Pepsi");
        snack.setPrice(3.00);
        snack.setQuantity(10);

        assertEquals("Pepsi", snack.getName());
        assertEquals(3.00, snack.getPrice());
        assertEquals(10, snack.getQuantity());
    }
}
