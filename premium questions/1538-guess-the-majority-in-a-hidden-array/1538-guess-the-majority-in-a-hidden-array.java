/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 *     public int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     public int length();
 * };
 */

class Solution {
        int countEqual = 1;
        int countDiff = 0;
        int indexDiffer = -1;

    public int guessMajority(ArrayReader reader) {
        int query0123 = reader.query(0, 1, 2, 3);
        int query1234 = reader.query(1, 2, 3, 4);

        handle(query0123 == query1234, 4);

        for(int i = 5; i < reader.length(); i++) {
            handle(reader.query(1, 2, 3, i) == query0123, i);
        }

        handle(reader.query(0, 2, 3, 4) == query1234, 1);

        handle(reader.query(0, 1, 3, 4) == query1234, 2);

        handle(reader.query(0, 1, 2, 4) == query1234, 3);

        return countEqual > countDiff ? 0 : (countEqual < countDiff ? indexDiffer : -1);
    }

    void handle(boolean equal, int index) {
        if (equal) {
            countEqual++;
        } else {
            countDiff++;
            indexDiffer = index;
        }
    }
}