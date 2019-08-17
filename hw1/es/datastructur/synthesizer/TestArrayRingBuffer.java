package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(1);
        arb.enqueue(2);
        int x1 = arb.dequeue();
        arb.enqueue(3);
        arb.enqueue(4);
        int x2 = arb.dequeue();
        arb.enqueue(5);
        arb.enqueue(6);
        int x3 = arb.dequeue();
        int x4 = arb.dequeue();
        int x5 = arb.dequeue();
        arb.enqueue(7);


        ArrayRingBuffer<Integer> exped = new ArrayRingBuffer<>(5);
        exped.enqueue(6);
        exped.enqueue(7);

        assertEquals(arb, exped);

    }

}
