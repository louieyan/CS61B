import org.junit.Test;
import static org.junit.Assert.*;


public class TestSort {
    /** Tests the sort method of the Sort class. */
    @Test
    public void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        String expected = "an";
        String actual = input[Sort.findSmallest(input, 0)];
        assertEquals(expected, actual);

        String[] input2 = {"there", "are", "many", "pigs"};
        String expected2 = "are";
        String actual2 = input2[Sort.findSmallest(input2, 0)];
        assertEquals(expected2, actual2);
    }

    @Test
    public  void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};
        Sort.swap(input, a, b);
        assertEquals(input, expected);
    }

}