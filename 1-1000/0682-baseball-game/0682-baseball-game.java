class Solution {
    public int calPoints(String[] operations) {

        int[] scores= new int[operations.length];
        int pos=0;

        for (String op : operations) {
            if (op.equals("+")) {
                scores[pos] = scores[pos - 1] + scores[pos - 2];
                pos++;
            } else if (op.equals("D")) {
                scores[pos] = 2 * scores[pos - 1];
                pos++;
            } else if (op.equals("C")) {
                pos--;
            } else {
                scores[pos] = Integer.parseInt(op);
                pos++;
            }
        }

        int sum = 0;
        for (int i = 0; i < pos; i++) {
            sum += scores[i]; // Sum up all valid scores
        }

        return sum;
        
    }
}