class Solution {
    public long countPalindromePaths(List<Integer> parent, String s) {
        var n = parent.size();

        /*
         * Build the tree using an adjacency list represented by arrays.
         * head[u] stores the first outgoing edge from node u.
         */
        var head = new int[n];
        Arrays.fill(head, -1);

        var to = new int[n - 1];
        var next = new int[n - 1];

        var edgeIndex = 0;

        for (var node = 1; node < n; node++) {
            var parentNode = parent.get(node);

            to[edgeIndex] = node;
            next[edgeIndex] = head[parentNode];
            head[parentNode] = edgeIndex;

            edgeIndex++;
        }

        var mask = new int[n];
        var stack = new int[n];
        var stackPointer = 0;

        stack[stackPointer++] = 0;

        /*
         * Traverse the tree from the root.
         * mask[node] stores the parity mask of characters
         * on the path from the root to that node.
         */
        while (stackPointer > 0) {
            var currentNode = stack[--stackPointer];

            for (var edge = head[currentNode]; edge != -1; edge = next[edge]) {
                var childNode = to[edge];
                var characterBit = 1 << (s.charAt(childNode) - 'a');

                mask[childNode] = mask[currentNode] ^ characterBit;
                stack[stackPointer++] = childNode;
            }
        }

        var frequency = new IntLongHashMap(n * 2);
        var answer = 0L;

        for (var node = 0; node < n; node++) {
            var currentMask = mask[node];

            /*
             * Same masks mean every character on the path
             * between two nodes appears an even number of times.
             */
            answer += frequency.get(currentMask);

            /*
             * Masks differing by exactly one bit mean that
             * exactly one character has an odd frequency.
             */
            for (var bit = 0; bit < 26; bit++) {
                answer += frequency.get(currentMask ^ (1 << bit));
            }

            // Store the current mask for future node pairs.
            frequency.add(currentMask, 1);
        }

        return answer;
    }

    static final class IntLongHashMap {
        private static final int EMPTY = Integer.MIN_VALUE;

        private int[] keys;
        private long[] values;
        private int mask;
        private int size;
        private int maxFill;

        IntLongHashMap(int expected) {
            var capacity = 1;
            var requiredCapacity = (expected * 10) / 7 + 1;

            while (capacity < requiredCapacity) {
                capacity <<= 1;
            }

            keys = new int[capacity];
            values = new long[capacity];
            Arrays.fill(keys, EMPTY);

            mask = capacity - 1;
            maxFill = (capacity * 7) / 10;
        }

        long get(int key) {
            var position = mix(key) & mask;

            while (true) {
                var currentKey = keys[position];

                if (currentKey == EMPTY) {
                    return 0L;
                }

                if (currentKey == key) {
                    return values[position];
                }

                position = (position + 1) & mask;
            }
        }

        void add(int key, long delta) {
            if (size >= maxFill) {
                rehash(keys.length << 1);
            }

            var position = mix(key) & mask;

            while (true) {
                var currentKey = keys[position];

                if (currentKey == EMPTY) {
                    keys[position] = key;
                    values[position] = delta;
                    size++;
                    return;
                }

                if (currentKey == key) {
                    values[position] += delta;
                    return;
                }

                position = (position + 1) & mask;
            }
        }

        private void rehash(int newCapacity) {
            var oldKeys = keys;
            var oldValues = values;

            keys = new int[newCapacity];
            values = new long[newCapacity];
            Arrays.fill(keys, EMPTY);

            mask = newCapacity - 1;
            maxFill = (newCapacity * 7) / 10;
            size = 0;

            for (var i = 0; i < oldKeys.length; i++) {
                var key = oldKeys[i];

                if (key != EMPTY) {
                    add(key, oldValues[i]);
                }
            }
        }

        private static int mix(int value) {
            value ^= value >>> 16;
            value *= 0x7feb352d;
            value ^= value >>> 15;
            value *= 0x846ca68b;
            value ^= value >>> 16;

            return value;
        }
    }
}