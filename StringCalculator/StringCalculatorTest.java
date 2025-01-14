import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class StringCalculatorTest {

    @Test
    public void Test012Sum() {
        Assertions.assertEquals(0, StringCalculator.Add(""));
        Assertions.assertEquals(1, StringCalculator.Add("1"));
        Assertions.assertEquals(3, StringCalculator.Add("1,2"));
    }

    @Test
    public void Test012345Sum() {
        Assertions.assertEquals(15, StringCalculator.Add("1,2,3,4,5"));
    }

    @Test
    public void TestSautSum() {
        Assertions.assertEquals(6, StringCalculator.Add("1\n2,3"));
    }

    @Test
    public void TestGetDelimiteur() {
        Assertions.assertEquals(";", StringCalculator.getDelimiteur("\\;\n1,2;3"));
    }

    @Test
    public void TestDelimiteurPerso() {
        Assertions.assertEquals(6, StringCalculator.Add("\\;\n1,2;3"));
    }

    @Test
    public void TestNombresNegatifs() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> StringCalculator.Add("-5,2,-10,9"));
        Assertions.assertEquals("Les nombres négatifs ne sont pas autorisés : -5,-10", runtimeException.getMessage());
    }

    @Test
    public void TestNombresNegatifsWithDelimteur() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> StringCalculator.Add("\\;,-5,2;-10,9"));
        Assertions.assertEquals("Les nombres négatifs ne sont pas autorisés : -5,-10", runtimeException.getMessage());
    }

    @Test
    public void TestIgnoreOver1000() {
        Assertions.assertEquals(6, StringCalculator.Add("1,2,3,1000"));
    }
}