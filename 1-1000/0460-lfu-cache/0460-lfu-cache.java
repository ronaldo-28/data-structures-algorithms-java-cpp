class FreqNode {
    int freq;
    FreqNode prev;
    FreqNode next;
    DataNode head;
    DataNode tail;

    FreqNode(int freq) {
        this.freq = freq; 
    }
}
class DataNode {
    int key;
    int val;
    DataNode prev;
    DataNode next;
    FreqNode freq;

    DataNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
class LFUCache {
    int capacity;
    int curSize;
    DataNode[] map;

    FreqNode head;
    FreqNode tail;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new DataNode[100001];
    }
    
    public int get(int key) {
        DataNode data = map[key];
        if (data == null) 
            return -1;

        FreqNode freq = data.freq;
        FreqNode nextFreq = freq.next;

        if (nextFreq == null || nextFreq.freq != freq.freq + 1) {
            freq.next = new FreqNode(freq.freq + 1);
            freq.next.next = nextFreq;
            freq.next.prev = freq;
            if (nextFreq != null)
                nextFreq.prev = freq.next;
            nextFreq = freq.next;
            if (freq == head)
                head = nextFreq;
        }

        if (data != freq.head)
            data.prev.next = data.next;
        else
            freq.head = data.next;
        
        if (data != freq.tail)
            data.next.prev = data.prev;
        else
            freq.tail = data.prev;
        
        if (freq.head != null)
            freq.head.prev = freq.tail.next = null;
        else {
            if (freq != tail) {
                freq.prev.next = freq.next;
            }
            else {
                tail = freq.next;
            }
            
            freq.next.prev = freq.prev;
        }

        if (nextFreq.tail != null) {
            nextFreq.tail.next = data;
            data.prev = nextFreq.tail;
        } else {
            nextFreq.head = data;
            data.prev = null;           
        }
        
        data.next = null;
        nextFreq.tail = data;
        data.freq = nextFreq;
        return data.val;
    }
    
    public void put(int key, int value) {
        if (get(key) != -1) {
            map[key].val = value;
            return;
        }

        DataNode cur = new DataNode(key, value);
        FreqNode temp = tail;
        if (tail != null && tail.freq == 1) {
            tail.tail.next = cur;
            cur.prev = tail.tail;
            tail.tail = cur;
        } else {
            FreqNode curFreq = new FreqNode(1);

            if (tail == null)
                head = tail = curFreq;
            else {
                tail.prev = curFreq;
                curFreq.next = tail;
                tail = curFreq;
            }
            tail.head = tail.tail = cur;
        }

        cur.freq = tail;
        if (curSize++ == capacity) {
            map[temp.head.key] = null;

            temp.head = temp.head.next;
            if (temp.head != null)
                temp.head.prev = null;
            else {
                tail.next = temp.next;
                if (temp != head)
                    temp.next.prev = tail;
                else
                    head = tail;
            }
            curSize--;
        }

        map[key] = cur;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
/*
class LFUCache {

    private Map<Integer, Pair<Integer, Integer>> cache;
    private Map<Integer, LinkedHashSet<Integer>> frequencies;
    private int minf;
    private int capacity;

    private void insert(int key, int frequency, int value) {
        cache.put(key, new Pair<>(frequency, value));
        frequencies.putIfAbsent(frequency, new LinkedHashSet<>());
        frequencies.get(frequency).add(key);
    }

    public LFUCache(int capacity) {
        cache = new HashMap<>();
        frequencies = new HashMap<>();
        minf = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue == null) {
            return -1;
        }
        final int frequency = frequencyAndValue.getKey();
        final Set<Integer> keys = frequencies.get(frequency);
        keys.remove(key);
        if (keys.isEmpty()) {
            frequencies.remove(frequency);

            if (minf == frequency) {
                ++minf;
            }
        }
        final int value = frequencyAndValue.getValue();
        insert(key, frequency + 1, value);
        return value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue != null) {
            cache.put(key, new Pair<>(frequencyAndValue.getKey(), value));
            get(key);
            return;
        }
        if (capacity == cache.size()) {
            final Set<Integer> keys = frequencies.get(minf);
            final int keyToDelete = keys.iterator().next();
            cache.remove(keyToDelete);
            keys.remove(keyToDelete);
            if (keys.isEmpty()) {
                frequencies.remove(minf);
            }
        }
        minf = 1;
        insert(key, 1, value);
    }
}
*/

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */