class Node {
    int id;
    int score;
    
    Node prev;
    Node next;

    public Node(int id, int score) {
        this.id = id;
        this.score = score;
    }
}

class Leaderboard {
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public Leaderboard() {
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;

        map = new HashMap<>();
    }
    
    public void addScore(int playerId, int score) {
        Node player = map.get(playerId);

        if (player == null) {
            player = new Node(playerId, score);
            insert(player);
            map.put(playerId, player);
        } else {
            player.score += score;
            remove(player);
            insert(player);
        }
    }
    
    public int top(int K) {
        int sum = 0;
        Node cur = head.next;

        while (K > 0) {
            sum += cur.score;
            cur = cur.next;
            K--;
        }

        return sum;
    }
    
    public void reset(int playerId) {
        Node player = map.get(playerId);
        remove(player);
        map.remove(playerId);
    }

    public void insert(Node n) {
        Node cur = head.next;

        while (cur != tail && cur.score > n.score) {
            cur = cur.next;
        }

        Node prev = cur.prev;
        n.prev = prev;
        prev.next = n;
        n.next = cur;
        cur.prev = n;
    }

    public void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */