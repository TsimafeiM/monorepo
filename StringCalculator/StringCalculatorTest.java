import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class StringCalculatorTest {

    static Stream<Arguments> testDataSimpleSum() {
        return Stream.of(
                Arguments.of(0, "0"),
                Arguments.of(1, "1"),
                Arguments.of(3, "3"),
                Arguments.of(15, "1,2,3,4,5")
        );
    }

    @ParameterizedTest
    @MethodSource("testDataSimpleSum")
    void testSimpleSum(int expected, String entry) {
        Assertions.assertEquals(expected, StringCalculator.add(entry));
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