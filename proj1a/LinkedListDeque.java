public class LinkedListDeque<T> {
    public class LinkNode {
        public LinkNode prev;
        public T item;
        public LinkNode next;

        public LinkNode() {

        }
        public LinkNode(T i, LinkNode p, LinkNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private LinkNode sentinel;
    private int size;

    /**
     * Creates an empty deque.
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new LinkNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * Creates a deep copy of other.
     * @param other
     */
    public LinkedListDeque(LinkedListDeque other) {
        this();
        int index = 0;
        int otherSize = other.size();
        while (index < otherSize) {
            T i = (T) other.get(index);
            this.addLast(i);
            index += 1;
        }
    }
    /**
     * Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        sentinel.next = new LinkNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**
     * Adds an item of type T to the last of the deque.
     * @param item
     */
    public void addLast(T item) {
        sentinel.prev.next = new LinkNode(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return false/true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque.
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        LinkNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.printf("%n");
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T i = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        return i;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * @return
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T i = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return i;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index
     * @return
     */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        LinkNode p = sentinel;
        while (index >= 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }


    public T getRecursiveHelper(LinkNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index-1);
    }
    /**
     * Same function as get method, but use recursion instead of iteration.
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> l = new LinkedListDeque<>();
        l.addFirst(3);
        l.addFirst(2);
        l.addFirst(1);
        l.addLast(4);
        //l.removeFirst();
        //l.removeLast();
        l.printDeque();
        //l2.addFirst(3);
        LinkedListDeque<Integer> l2 = new LinkedListDeque<>(l);
        l2.printDeque();
        l2.removeLast();
        l2.printDeque();
        l.printDeque();

        System.out.println(l.getRecursive(2));
    }
}
