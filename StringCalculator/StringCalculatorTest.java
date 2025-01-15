import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class StringCalculatorTest {

    @Test
    void test012Sum() {
        Assertions.assertEquals(0, StringCalculator.add(""));
        Assertions.assertEquals(1, StringCalculator.add("1"));
        Assertions.assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    void test012345Sum() {
        Assertions.assertEquals(15, StringCalculator.add("1,2,3,4,5"));
    }

    @Test
    void testSkipLineSum() {
        Assertions.assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    void testAllDelimiteursRegex() {
        Assertions.assertEquals("[;,\n]", StringCalculator.allDelimiteursRegex("\\;\n1,2;3"));
    }

    @Test
    void testDelimiteurPerso() {
        Assertions.assertEquals(6, StringCalculator.add("\\;\n1,2;3"));
    }

    @Test
    void testNombresNegatifs() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> StringCalculator.add("-5,2,-10,9"));
        Assertions.assertEquals("Les nombres négatifs ne sont pas autorisés : -5,-10", runtimeException.getMessage());
    }

    @Test
    void testNombresNegatifsWithDelimteur() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> StringCalculator.add("\\;,-5,2;-10,9"));
        Assertions.assertEquals("Les nombres négatifs ne sont pas autorisés : -5,-10", runtimeException.getMessage());
    }

    @Test
    void testIgnoreOver1000() {
        Assertions.assertEquals(6, StringCalculator.add("1,2,3,1000"));
    }
}