
public class UnionFind {

    private int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        int n = parent.length;
        if (vertex < 0 || vertex >= n) {
            throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (n - 1));
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return -parent[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) {
            return;
        }
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        int sizeV1 = -parent[rootV1];
        int sizeV2 = -parent[rootV2];
        if (sizeV2 < sizeV1) { // Size of set v1 is bigger than size of set v2
             parent[rootV1] -= sizeV2;
             parent[rootV2] = rootV1;
        } else { // when size are equal, connect v1 to v2
            parent[rootV2] -= sizeV1;
            parent[rootV1] = rootV2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time.
       I comment the first version of path compression which uses an List to
       record the path.
       */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        //List<Integer> path = new ArrayList<>();
        while (parent[root] >= 0) { // don't forget the equal
            //path.add(next);
            root = parent[root];
        }

        /**
        for (Integer x : path) {
            parent[x] = next;
        }
         */
        while (vertex != root) {
            int nextVertex = parent[vertex];
            parent[vertex] = root;
            vertex = nextVertex;
        }
        return root;
    }

}
