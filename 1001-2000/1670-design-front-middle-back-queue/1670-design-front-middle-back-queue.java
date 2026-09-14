class FrontMiddleBackQueue {
    private final Node head, tail;
    private Node mid;
    private int size;

    public FrontMiddleBackQueue() {
        this.head = new Node(-1);
        this.tail = new Node(-1);
        this.mid = head;

        head.next = tail;
        tail.prev = head;

        this.size = 0;
    }
    
    public void pushFront(final int val) {
        final Node node = new Node(val);

        addNext(head, node);

        size++;

        if(size == 1)
            mid = node;
        else if(size % 2 == 0)
            mid = mid.prev;
    }
    
    public void pushMiddle(final int val) {
        final Node node = new Node(val);

        if(size % 2 == 0)
            addNext(mid, node);
        else
            addPrev(mid, node);

        mid = node;

        size++;
    }
    
    public void pushBack(final int val) {
        final Node node = new Node(val);

        addPrev(tail, node);

        size++;

        if(size % 2 == 1)
            mid = mid.next;
    }
    
    public int popFront() {
        if(size == 0)
            return -1;

        final int res = head.next.val;

        head.next.next.prev = head;
        head.next = head.next.next;

        if(size == 1)
            mid = head;
        else if(size % 2 == 0)
            mid = mid.next;

        size--;

        return res;
    }
    
    public int popMiddle() {
        if(size == 0)
            return -1;

        final int res = mid.val;

        mid.prev.next = mid.next;
        mid.next.prev = mid.prev;

        if(size % 2 == 0)
            mid = mid.next;
        else
            mid = mid.prev;

        size--;

        return res;
    }
    
    public int popBack() {
        if(size == 0)
            return -1;

        final int res = tail.prev.val;

        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;

        if(size % 2 == 1)
            mid = mid.prev;

        size--;

        return res;
    }

    private void addNext(final Node node, final Node newNode) {
        newNode.prev = node;
        newNode.next = node.next;

        node.next.prev = newNode;
        node.next = newNode;
    }

    private void addPrev(final Node node, final Node newNode) {
        newNode.next = node;
        newNode.prev = node.prev;

        node.prev.next = newNode;
        node.prev = newNode;
    }

    public class Node {
        public Node prev, next;
        public final int val;

        public Node(final int val) {
            this.val = val;
        }
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */