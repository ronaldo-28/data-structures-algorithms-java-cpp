class Allocator {
    class ListItem {
        int index;
        int size;
        int mID;
        ListItem prev;
        ListItem next;
    }

    ListItem head;

    public Allocator(int n) {
        head = new ListItem();
        head.index = 0;
        head.size = n;
        head.mID = 0;
        head.prev = head.next = null;
    }
    
    public int allocate(int size, int mID) {
        for (ListItem p = head; p != null; p = p.next) {
            if (p.size >= size && p.mID == 0) {
                int remaining = p.size - size;
                p.size = size;
                p.mID = mID;
                if (remaining > 0) {
                    ListItem n = new ListItem();
                    n.index = p.index + size;
                    n.size = remaining;
                    n.mID = 0;
                    n.next = p.next;
                    if (p.next != null) {
                        p.next.prev = n;
                    }
                    n.prev = p;
                    p.next = n;
                }
                return p.index;
            }
        }
        return -1;
    }
    
    public int freeMemory(int mID) {
        int result = 0;
        for (ListItem p = head; p != null; p = p.next) {
            if (p.mID == mID) {
                result += p.size;
                p.mID = 0;
                while (p.prev != null && p.prev.mID == 0) {
                    int addSize = p.size;
                    if (p.next != null) {
                        p.next.prev = p.prev;
                    }
                    p = p.prev;
                    p.size += addSize;
                    p.next = p.next.next;
                }
                while (p.next != null && p.next.mID == 0) {
                    int addSize = p.next.size;
                    p.size += addSize;
                    if (p.next.next != null) {
                        p.next.next.prev = p;
                    }
                    p.next = p.next.next;
                }
            }
        }
        return result;
    }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.freeMemory(mID);
 */