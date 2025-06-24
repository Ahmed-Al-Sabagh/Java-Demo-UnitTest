import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.InputMismatchException;

public class DemoTest {

    // ————— اختبارات isTriangle —————

    @Test public void testValidTriangle() {
        assertTrue(Demo.isTriangle(3,4,5));
    }

    @Test public void testDecimalTriangle() {
        assertTrue(Demo.isTriangle(3.5,4.1,5.2));
    }

    @Test public void testAsymmetricValid() {
        assertTrue(Demo.isTriangle(10,6,7));
    }

    @Test public void testDifferentOrders() {
        assertTrue(Demo.isTriangle(5,3,4));
        assertTrue(Demo.isTriangle(4,5,3));
    }

    @Test public void testLargeSides() {
        assertTrue(Demo.isTriangle(1000,1000,1000));
    }

    @Test public void testNegativeSide() {
        assertFalse(Demo.isTriangle(-1,2,2));
    }

    @Test public void testZeroSides() {
        assertFalse(Demo.isTriangle(0,5,5));
    }

    @Test public void testSumEqualThirdEach() {
        assertFalse(Demo.isTriangle(5,5,10));
        assertFalse(Demo.isTriangle(5,10,5));
        assertFalse(Demo.isTriangle(10,5,5));
    }

    @Test public void testSumLessThanThirdEach() {
        assertFalse(Demo.isTriangle(2,2,5));
        assertFalse(Demo.isTriangle(2,5,2));
        assertFalse(Demo.isTriangle(5,2,2));
    }

    @Test public void testOnlyTwoConditionsTrue() {
        assertFalse(Demo.isTriangle(3,3,7));
        assertFalse(Demo.isTriangle(3,7,3));
        assertFalse(Demo.isTriangle(7,3,3));
    }

    @Test public void testAlmostValidEdge() {
        assertTrue(Demo.isTriangle(5,5,9.9));
    }

    @Test public void testNaNInfinity() {
        assertFalse(Demo.isTriangle(Double.NaN,4,5));
        assertFalse(Demo.isTriangle(4,Double.NaN,5));
        assertFalse(Demo.isTriangle(4,5,Double.NaN));
        assertFalse(Demo.isTriangle(Double.POSITIVE_INFINITY,4,5));
        assertFalse(Demo.isTriangle(4,Double.NEGATIVE_INFINITY,5));
        assertFalse(Demo.isTriangle(4,5,Double.POSITIVE_INFINITY));
    }

    // ————— اختبارات main() —————

    @Test public void testMainValid() throws Exception {
        assertMain("3\n4\n5\n", "this is a triangle");
    }

    @Test public void testMainInvalid() throws Exception {
        assertMain("1\n2\n3\n", "not a triangle");
    }

    @Test public void testMainAlmost() throws Exception {
        assertMain("5\n5\n10\n", "not a triangle");
    }

    @Test public void testMainZeroSides() throws Exception {
        assertMain("0\n0\n0\n", "not a triangle");
    }

    @Test public void testMainNegative() throws Exception {
        assertMain("-1\n2\n2\n", "not a triangle");
    }

    @Test public void testMainDecimalInput() {
        try {
            assertMain("3.5\n4.1\n5.2\n", null);
            fail("Expected InputMismatchException for decimal input");
        } catch (InputMismatchException e) {
            // صحيح؛ السكَنّر nextInt سيفشل
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test public void testMainNonNumeric() {
        try {
            assertMain("a\nb\nc\n", null);
            fail("Expected InputMismatchException for non-numeric input");
        } catch (InputMismatchException e) {
            // متوقع
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    // ————— دالة مساعدة —————

    private void assertMain(String input, String expected) throws Exception {
        InputStream in = System.in;
        PrintStream out = System.out;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            Demo.main(null);
            if (expected != null) {
                assertTrue(baos.toString().toLowerCase().contains(expected));
            }
        } finally {
            System.setIn(in);
            System.setOut(out);
        }
    }
}
