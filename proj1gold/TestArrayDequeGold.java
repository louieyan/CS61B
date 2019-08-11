import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> stuDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solDeque = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder("Call stacks:\n");

        for (int i = 0; i < 60; i++) {
            double numberBetweenZeroAndOne1 = StdRandom.uniform();
            double numberBetweenZeroAndOne2 = StdRandom.uniform();

            if (numberBetweenZeroAndOne1 < 0.5) {
                /** Call add methods when first random number < 0.5 */
                if (numberBetweenZeroAndOne2 < 0.5) {
                    /** Call addFirst when second random number < 0.5 */
                    stuDeque.addFirst(i);
                    solDeque.addFirst(i);
                    message.append("addFirst(" + i + ")\n");
                } else { /** Call addLast when second random number > 0.5 */
                    stuDeque.addLast(i);
                    solDeque.addLast(i);
                    message.append("addLast(" + i + ")\n");
                }
                /** What if the first number > 0.5 ? */
            } else if (!stuDeque.isEmpty() && !solDeque.isEmpty()) {
                if (numberBetweenZeroAndOne2 < 0.5) {
                    /** If the second number < 0.5, call removeFirst */
                    Integer expected = stuDeque.removeFirst();
                    Integer actual = solDeque.removeFirst();
                    message.append("removeFirst()\n");
                    assertEquals(message.toString(), expected, actual);
                } else { /** If the second number > 0.5, call removeLast */
                    Integer expected = stuDeque.removeLast();
                    Integer actual = solDeque.removeLast();
                    message.append("removeLast()\n");
                    assertEquals(message.toString(), expected, actual);
                }

            }
        }
        //System.out.println(message.toString());
    }
}

