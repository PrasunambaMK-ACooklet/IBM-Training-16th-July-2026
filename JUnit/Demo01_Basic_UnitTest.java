package Junit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//import static org.testing.AssertJUnit.assertEquals;

class Demo01_Basic_UnitTest {

    @Test
    void testAddition() {
        Demo01_Calculator calc = new Demo01_Calculator();
        assertEquals(calc.add(10, 5), 15);
    }
    @Test
    void testAdditionFail() {
        Demo01_Calculator calc = new Demo01_Calculator();
        //fails
        assertEquals(20, calc.add(10, 5), "Expected 20 but actual is 15");
    }
}
