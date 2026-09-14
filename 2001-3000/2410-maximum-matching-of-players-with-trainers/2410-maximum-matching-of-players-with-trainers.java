class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Thread t1 = new Thread(() -> Arrays.sort(players));
        Thread t2 = new Thread(() -> Arrays.sort(trainers));
        t1.start(); t2.start();
        try { t1.join(); t2.join(); } catch (Exception ignored) {}
        int n = players.length;
        int m = trainers.length;
        int i = 0;
        int j = 0;
        int count = 0;
        while(i < n && j < m){
            if(players[i] <= trainers[j]){
                i++;
                count++;
            }
            j++;
        }
        return count;
    }
}