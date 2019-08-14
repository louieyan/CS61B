import static org.junit.Assert.*;
import org.junit.Test;

public class TestUnionFind {
    @Test
    public void testSizeOf() {
        /**
        UnionFind uf = new UnionFind(5);
        boolean actual = uf.connected(1, 2);
        assertFalse(actual);

        uf.union(1, 2);
        boolean actual2 = uf.connected(1, 2);
        assertTrue(actual2);

        int sizeOfOne = uf.sizeOf(1);
        assertEquals(2, sizeOfOne);

        int parentOfOne = uf.parent(1);
        assertEquals(2, parentOfOne);
         */

        UnionFind uf2 = new UnionFind(16);
        uf2.union(1, 2);
        uf2.union(3, 4);
        uf2.union(1, 4);
        uf2.union(7, 8);
        uf2.union(9, 10);
        uf2.union(7, 10);
        uf2.union(1, 7);
    }
}
