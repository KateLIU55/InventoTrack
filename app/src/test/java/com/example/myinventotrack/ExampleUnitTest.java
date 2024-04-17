import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        // Test addition
        assertEquals(4, 2 + 2);
    }

    @Test
    public void subtraction_isCorrect() {
        // Test subtraction
        assertEquals(0, 2 - 2);
    }

    @Test
    public void multiplication_isCorrect() {
        // Test multiplication
        assertEquals(6, 3 * 2);
    }

    @Test
    public void division_isCorrect() {
        // Test division
        assertEquals(2, 6 / 3);
    }
}
