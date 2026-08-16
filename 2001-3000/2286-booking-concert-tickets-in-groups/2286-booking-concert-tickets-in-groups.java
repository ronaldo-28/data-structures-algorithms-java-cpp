class BookMyShow {
    private IntervalTree tree;

    public BookMyShow(int n, int m) {
        tree = new IntervalTree(n - 1, m);
    }

    public int[] gather(int k, int maxRow) {
        return tree.bookGather(k, maxRow);
    }

    public boolean scatter(int k, int maxRow) {
        return tree.bookScatter(k, maxRow);
    }
}

class IntervalTreeNode {
    private int start;
    private int end;
    private int mid;
    private int capacity;
    private int max;
    private long total;
    private IntervalTreeNode left;
    private IntervalTreeNode right;

    public IntervalTreeNode(int start, int end, int capacity) {
        this.start = start;
        this.end = end;
        this.mid = (start + end) / 2;
        this.capacity = capacity;
        this.max = capacity;
        this.total = (end - start + 1L) * capacity;

        if (start != end) {
            this.left = new IntervalTreeNode(start, mid, capacity);
            this.right = new IntervalTreeNode(mid + 1, end, capacity);
        }
    }

    public int[] bookGather(int number, int maxRow) {
        if (start == end) {
            if (this.total >= number) {
                int[] booking = { start, capacity - max };
                this.max -= number;
                this.total -= number;
                return booking;
            } else {
                return new int[0];
            }
        }

        int[] res = new int[0];
        if (this.left.max >= number) {
            res = this.left.bookGather(number, maxRow);
        } else if (maxRow >= mid + 1 && this.right.max >= number) {
            res = this.right.bookGather(number, maxRow);
        }

        if (res.length > 0) {
            updateNode();
        }

        return res;
    }

    public long checkScatter(int maxRow) {
        if (start == end || maxRow == end) {
            return total;
        }

        if (maxRow <= mid) {
            return left.checkScatter(maxRow);
        }

        return left.total + right.checkScatter(maxRow);
    }

    public void bookScatter(long number) {
        if (start != end) {
            if (this.left.total < number) {
                this.right.bookScatter(number - this.left.total);
            }
            this.left.bookScatter(Math.min(this.left.total, number));
            updateNode();
        } else {
            this.total -= number;
            this.max -= number;
        }
    }

    private void updateNode() {
        this.total = left.total + right.total;
        this.max = Math.max(left.max, right.max);
    }
}

class IntervalTree {
    private IntervalTreeNode root;

    public IntervalTree(int end, int capacity) {
        root = new IntervalTreeNode(0, end, capacity);
    }

    public int[] bookGather(int number, int maxRow) {
        return root.bookGather(number, maxRow);
    }

    public boolean bookScatter(int number, int maxRow) {
        if (root.checkScatter(maxRow) >= number) {
            root.bookScatter(number);
            return true;
        }
        return false;
    }
}