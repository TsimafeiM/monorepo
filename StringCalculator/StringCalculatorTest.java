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
}