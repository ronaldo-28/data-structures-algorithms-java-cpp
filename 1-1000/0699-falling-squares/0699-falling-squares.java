class Solution {
    class Range {
        int left, right, top;
        Range prev, next;

        Range(int left, int right, int top, Range prev, Range next) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.prev = prev;
            this.next = next;
        }

        boolean isWithin(int begin, int end) {
            return begin < this.right && end > this.left;
            /*
            int min = Math.max(this.left, begin);
            int max = Math.min(this.right, end);
            return min < max;
            */
        }

        @Override
        public String toString() {
            return "(" + this.left + "," + this.right + "," + this.top + ")";
        }
    }
    Range head, tail;
    
    public List<Integer> fallingSquares(int[][] positions) {
        head = new Range(0, 0, 0, null, null);
        tail = new Range(0, 0, 0, head, null);
        head.next = tail;

        int height = 0;
        List<Integer> ans = new ArrayList<>();
        for (int[] p : positions) {
            height = Math.max(height, add(p[0], p[0] + p[1], p[1]));
            ans.add(height);
        }

        return ans;
    }

    private int add(int left, int right, int size) {
        // System.out.println(left + "," + right + "," + size);
        Range node = head.next;
        while (node != tail) {
            if (node.left >= right) {
                // add new item
                addBefore(left, right, size, node);
                return size;
            }

            if (node.isWithin(left, right)) {
                Range start = node;
                int topMax = node.top;
                // System.out.println("    start at (" + start + ")");

                while (node.next != tail && node.next.isWithin(left, right)) {
                    node = node.next;
                    topMax = Math.max(topMax, node.top);
                }
                
                // System.out.println("    end at (" + node + ") with " + topMax);
                if (start == node) {
                    if (left <= start.left && right >= start.right) {
                        start.left = left;
                        start.right = right;
                        start.top = topMax + size;
                    } else if (left > start.left && right < start.right) {
                        // Split with 3 blocks
                        addBefore(start.left, left, start.top, start);
                        addAfter(right, start.right, start.top, start);
                        start.left = left;
                        start.right = right;
                        start.top = topMax + size;
                    } else if (left <= start.left) {
                        addBefore(left, right, topMax + size, start);
                        start.left = right;
                    } else {
                        addBefore(start.left, left, start.top, start);
                        start.left = left;
                        start.right = right;
                        start.top = topMax + size;
                    }
                } else {
                    if (left <= start.left) {
                        start.left = left;
                        start.top = topMax + size;
                    } else {
                        addBefore(start.left, left, start.top, start);
                        start.left = left;
                    }

                    if (right < node.right) {
                        addAfter(right, node.right, node.top, node);
                    }

                    // merge the left and right
                    start.right = right;
                    start.top = topMax + size;
                    start.next = node.next;
                    node.next.prev = start;
                }
                
                // printList();
                return topMax + size;
            }

            node = node.next;
        }

        // add at the end
        node = new Range(left, right, size, tail.prev, tail);
        tail.prev.next = node;
        tail.prev = node;
        // printList();
        return size;
    }

    private void addBefore(int left, int right, int top, Range ref) {
        Range newNode = new Range(left, right, top, ref.prev, ref);
        ref.prev.next = newNode;
        ref.prev = newNode;
    }

    private void addAfter(int left, int right, int top, Range ref) {
        Range newNode = new Range(left, right, top, ref, ref.next);
        ref.next.prev = newNode;
        ref.next = newNode;
    }

    private void printList() {
        Range node = head.next;
        System.out.print("nodes");
        while (node != tail) {
            System.out.print(node);
            node = node.next;
        }
        System.out.println("");
    }
}