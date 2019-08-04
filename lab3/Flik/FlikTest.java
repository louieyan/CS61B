import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testIsSameNumber() {
        int a = 129;
        int b = 129;
        assertTrue(Flik.isSameNumber(a, b));
    }
}
