public class ArrayDeque<T> {
	/** When I implement this class I find using circular array has a difficult
	*   problem: HOW YOU GET THE LOGICAL ORDER?
	*   After I did some searching, I find useful information at: http://1t.click/5zX
	*   I stole the resize and bitwise method from it.
	*/
	
	
    private static final int SIZE_FACTOR = 2;
    private static final double USAGE_FACTOR_LIMIT = 0.25;
    private double usageFactor; // when usageFactor is small than USAGE_FACTOR_LIMIT, we halve the capacity
    private T[] items;
    private int size;
    private int first; // The first element of the deque.
    private int nextLast;  // The next last element of the deque.


    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 0;
        nextLast = 0;
        usageFactor = 0;
    }

    /** Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            T x = (T) other.get(i);
            this.addLast(x);
        }
    }
	
    /** Adds an item of type T to the front of deque */
    public void addFirst(T x) {
        first = moveFirstAdd(first);
        items[first] = x;
        size += 1;
        computeUsageFactor();
        if (first == nextLast) {
            doubleCapacity();
        }
    }
	
	/** Adds an item of type T to the end of deque */
    public void addLast(T x) {
        items[nextLast] = x;
        size += 1;
        nextLast = moveNextLastAdd(nextLast);
        computeUsageFactor();
        if (first == nextLast) {
           doubleCapacity();
       }
    }
	
	/** Returns whether the deque is empty. */
    public boolean isEmpty() {
        return size == 0;
    }
	
	/** Returns the size of the deque. */
    public int size() {
        return size;
    }
	
	/** Prints the content of the deque */
    public void printDeque() {	// Uses get method to print the logically right order
        for (int i = 0; i < size; i++) { // not just print the underlying array in order
            System.out.print(this.get(i) + " "); // which gives us a physically correct result.
        }
        System.out.printf("%n");
    }

	/** Removes and returns the first item of the deque. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = items[first];
        items[first] = null;
        first = moveFirstRemove(first);
        size -= 1;
        computeUsageFactor();
        if (items.length >= 16 && USAGE_FACTOR_LIMIT > usageFactor) {
            halveCapacity();
        }
        return x;
    }

	/** Removes and returns the last item of the deque. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = moveNextLastRemove(nextLast);
        T x = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        computeUsageFactor();
        if (items.length >= 16 && USAGE_FACTOR_LIMIT > usageFactor) {
            halveCapacity();
        }
        return x;
    }
	
	/** Gets the item at index */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
		// (first + num) & (items.length - 1) is a bitwise and operation between the two numbers.
		// num is index here in this function.
		// But num can be 1 or -1 to replace functions like moveFirstAdd. moveNextLastAdd and moveFirstRemove, moveNextLastRemove.
		// The items.length is always a multiple of 2, so the binary representation of items.length - 1 
		// have the pattern looks like: 0000 ... 011111111
		// so when you do bitwise and, if (first + num) < items.length
		// it will return first + num after bitwise
		// but when (first + num) >= items.length, the return number is the result if you see the array is circular.
        return items[(first + index) & (items.length - 1)]; 
     }



    /**
     * Some useful helper functions.
     */

    /** Double the array when first = nextLast */
    private void doubleCapacity() {
        int p = first;
        int r = items.length - p;
        T[] a = (T[]) new Object[size * SIZE_FACTOR];
        System.arraycopy(items, p, a, 0, r); // When double items' capacity, we rearrange the full array
        System.arraycopy(items, 0, a, r, p); // after copy all content of the original array
        items = a;							// the new array looks like:
        first = 0;							// [first, second, ..., last, null, ..., null]
        nextLast = size;					// rather than the full original array which may looks like:
        computeUsageFactor();				// [... ,(last-1)Item ,lastItem, firstItem, secondItem, ...]
    }

    /** Halve an array when usage factor is under USAGE_FACTOR_LIMIT */
    private void halveCapacity() {
        if (first < nextLast) { // When the underlying array looks like
								// [null, ..., null, firstItem, ..., lastItem, null, ...]
            T[] a = (T[]) new Object[items.length / SIZE_FACTOR];
            System.arraycopy(items, first, a, 0, nextLast - first);
            items = a;
        } else { // When the underlying array looks like:
				 // [... ,(last-1)Item ,lastItem, null, ..., null, firstItem, secondItem, ...]
            int p = first;
            int r = items.length - p;
            T[] a = (T[]) new Object[items.length / SIZE_FACTOR];
            System.arraycopy(items, p, a, 0, r);
            System.arraycopy(items, 0, a, r, nextLast);
            items = a;
        }
        first = 0;
        nextLast = size;
        computeUsageFactor();
    }

    /** Moves first to the next index when add item */
    private int moveFirstAdd(int f) {
        if (f == 0) {
            f = items.length - 1;
        } else {
            f -= 1;
        }
        return f;
    }

    /** Moves nextLast to the next index when add item. */
    private int moveNextLastAdd(int nl) {
        if (nl == items.length - 1) {
            nl = 0;
        } else {
            nl += 1;
        }
        return nl;
    }

    /** Computes usage factor. */
    private void computeUsageFactor() {
		// Because both size and items.length are int, and size always less than
		// or equal to items.length, size / items.length will be 0.
		// So we convert int to double first.
        usageFactor = Double.valueOf(size) / Double.valueOf(items.length);
    }

    /** Moves first to the next index when remove item. */
    private int moveFirstRemove(int f) {
        if (f == items.length - 1) {
            f = 0;
        } else {
            f += 1;
        }
        return f;
    }

    /** Moves nextLast to the next index when add item. */
    private int moveNextLastRemove(int nl) {
        if (nl == 0) {
            nl = items.length - 1;
        } else {
            nl -= 1;
        }
        return nl;
    }


	/** An example to test the correctness of class methods. */
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= 13; i++) {
            deque.addLast(i);
        }
        for (int i = 1; i <= 2; i++) {
            deque.addFirst(i);
        }

        deque.printDeque();

        ArrayDeque<Integer> deque2 = new ArrayDeque<>(deque);
        deque2.printDeque();

        deque2.removeFirst();
        deque.printDeque();
        deque2.printDeque();
    }


}
