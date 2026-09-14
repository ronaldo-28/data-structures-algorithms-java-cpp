class AllOne {
    private class Node {
        String key;
        int val;
        Node next, prev;

        private Node(int val) {
            this.val = val;
        }

    }

    Map<String, Node> map;
    Node head, tail;

    public AllOne() {
        map = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    private void setHead(Node node) {
        Node temp = head.next;
        head.next = node;
        node.prev = head;
        node.next = temp;
        temp.prev = node;
    }

    private void setTail(Node node) {
        Node temp = tail.prev;
        tail.prev = node;
        node.next = tail;
        node.prev = temp;
        temp.next = node;
    }

    private void removeNode(Node node) {
        Node nxt = node.next;
        Node pre = node.prev;
        nxt.prev = pre;
        pre.next = nxt;
        node.prev = null;
        node.next = null;
    }

    public void inc(String key) {
        if (!map.containsKey(key)) {
            Node newNode = new Node(1);
            newNode.key = key;
            map.put(key, newNode);
            setHead(newNode);
        } else {
            Node curNode = map.get(key);
            curNode.val++;
            if (curNode.val > tail.prev.val) {
                removeNode(curNode);
                setTail(curNode);
            }
        }
    }

    public void dec(String key) {
        Node curNode = map.get(key);
        if (curNode.val == 1) {
            map.remove(key);
            removeNode(curNode);
        } else {
            curNode.val--;
            if (curNode.val < head.next.val) {
                Node pre = head.next;
                removeNode(curNode);
                setHead(curNode);
                if (pre.val > tail.prev.val) {
                    removeNode(pre);
                    setTail(pre);
                }
            }
        }
    }

    public String getMaxKey() {
        return (tail.prev == head) ? "" : tail.prev.key;
    }

    public String getMinKey() {
        return (head.next == tail) ? "" : head.next.key;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */