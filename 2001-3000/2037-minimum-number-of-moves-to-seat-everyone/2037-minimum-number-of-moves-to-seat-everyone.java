class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        int moves=0;
        int[] seatPos = new int[101];
        int[] studPos = new int[101];
        for (int s : seats) {
            seatPos[s]++;
        }
        for (int s : students) {
            studPos[s]++;
        }
        int i = 0; // pointer to seat
        int j = 0; // pointer to student
        while (j < 101) {
            while (i<101 && seatPos[i]==0) { i++; }
            if (i==101) break;
            while (j<101 && studPos[j]==0) { j++; }
            moves += Math.abs(j-i);
            seatPos[i]--;
            studPos[j]--;
        }
        return moves;
    }
}