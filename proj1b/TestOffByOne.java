import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        char x1 = 'a';
        char y1 = 'b';
        assertTrue(offByOne.equalChars(x1, y1));

        char x2 = 'd';
        char y2 = 'c';
        assertTrue(offByOne.equalChars(x2, y2));

        char x3 = 'a';
        char y3 = 'e';
        assertFalse(offByOne.equalChars(x3, y3));

    }
}