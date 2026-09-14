class Solution {

    private void dfsSteppingNumbers(List<Integer> stepping, int num, int low, int high) {

        if (num >= low && num <= high) stepping.add(num);
        if (num >= high) return;
        if (num > Integer.MAX_VALUE / 10) return;

        int prev = num % 10;

        if (prev != 0) {
            dfsSteppingNumbers(stepping, (num * 10) + (prev - 1), low, high);
        }

        if (prev != 9) {
            dfsSteppingNumbers(stepping, (num * 10) + (prev + 1), low, high);
        }


    }

    public List<Integer> countSteppingNumbers(int low, int high) {

        List<Integer> stepping = new ArrayList<>();
        
        if (low <= 0) stepping.add(0);
        for (int d = 1; d <= 9; d++) {
            dfsSteppingNumbers(stepping, d, low, high);
        }

        Collections.sort(stepping);

        return stepping;
        
    }
}