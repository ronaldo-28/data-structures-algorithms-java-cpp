public class RandomizedCollection {
    private static final int MASK = 0b1111111111111;
    private int size = 0x0;
    private static int seed = 0x5A5A5A5A;
    private final Bucket[] map = new Bucket[0b10000000000000];
    private final Pair[] elements = new Pair[0b10011100010000];

    public boolean insert(int val) {
        int index = val & MASK;
        if (map[index] == null) {
            map[index] = new Bucket();
        }
        Bucket bucket = map[index];
        bucket.elements[bucket.size] = size;
        elements[size++] = new Pair(val, bucket.size++);
        return bucket.size == 1;
    }

    public boolean remove(int val) {
        Bucket bucket = map[val & MASK];
        if (bucket == null || bucket.size == 0) {
            return false;
        }
        int removeIndex = bucket.elements[--bucket.size];
        Pair last = elements[--size];
        if (removeIndex != size) {
            elements[removeIndex] = last;
            map[last.val & MASK].elements[last.index] = removeIndex;
        }

        return true;
    }

    public int getRandom() {
        seed ^= (seed << 13);
        seed ^= (seed >>> 17);
        seed ^= (seed << 5);
        return elements[Math.abs(seed) % size].val;
    }

    private static class Bucket {
        private final int[] elements = new int[7];
        int size = 0;
    }

    private static class Pair {
        private final int val, index;
        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}