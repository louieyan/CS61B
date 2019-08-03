public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }


    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        IntNode p = first;
        while (position != 1 && p.next != null) {
            p = p.next;
            position--;
        }
        p.next = new IntNode(item, p.next);
    }

    public void reverse() {
        if (first == null || first.next == null) {
            return;
        }
        IntNode p = first.next;
        first.next = null;

        while (p != null) {
            IntNode temp = p.next;
            p.next = first;
            first = p;
            p = temp;
        }

    }

    public void reverseRecursive() {
        first = reverseRecursiveHelper(first);
    }

    private IntNode reverseRecursiveHelper(IntNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        IntNode temp = p.next;
        IntNode reversed = reverseRecursiveHelper(p.next);
        temp.next = p;
        p.next = null;
        return reversed;
    }
    public void printSLList() {
        IntNode p = first;
        while (p != null) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.printf("%n");
    }

    public static void main(String[] args) {
        SLList l = new  SLList();
        l.addFirst(3);
        l.addFirst(1);
        l.insert(2, 1);
        l.printSLList();
        l.reverseRecursive();
        l.printSLList();
    }
}