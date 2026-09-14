class MaxStack {
    class Node {
        int val;
        int ind;
        boolean isDeleted;
        Node next;
        Node prev;
        Node (int v, int ind) {
            this.val = v;
            this.ind = ind;
            this.isDeleted = false;
        }
    }

    
    PriorityQueue<Node>pq;
    //Set<Node> setNode; // logical mistake, forgot to use, this , need to remove from pq as well , if top element is pop;
    int currIndex = 0;
    Node head;
    Node tail;
    public MaxStack() {
        pq = new PriorityQueue<>((a,b) ->  {
            if(b.val == a.val)
                return Integer.compare(b.ind, a.ind);
        return Integer.compare(b.val, a.val);
    });

        head = new Node(-1, 0);
        tail = new Node(-1, 0);
        head.prev = tail;
        tail.next = head;
    }
    
    public void push(int x) {
       Node newNode = new Node(x, currIndex++); // missed : currIndex ++ here 
       addNode(newNode);
    }
    
    public int pop() {
        int poll = head.prev.val;
        remove(head.prev);
        return poll;
    }
    
    public int top() {
        return head.prev.val;
    }
    
    public int peekMax() {
         while(pq.peek().isDeleted)
            pq.poll();
        return pq.peek().val;
    }
    
    public int popMax() {
        while(pq.peek().isDeleted)
            pq.poll();
        int ans = pq.peek().val;
        remove(pq.poll());
        return ans;
    }

    private void addNode(Node newNode) {
        Node prevNode = head.prev;
        prevNode.next = newNode;
        newNode.prev = prevNode;
        head.prev = newNode;
        newNode.next = head;
        pq.add(newNode);
    }
    private void remove(Node delNode) {
        Node prevNode = delNode.prev;
        Node nextNode = delNode.next;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
        delNode.isDeleted = true;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */




 // 