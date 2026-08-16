class Solution {
    public long dividePlayers(int[] skill) {
        int[] count = new int[1001];

        long sum = 0;
        int n = skill.length;

        for (int s : skill) {
            count[s]++;
            sum += s;
        }

        // total pairs = n/2, so each pair must sum to:
        if ((sum * 2) % n != 0) return -1;

        int target = (int)(2 * sum / n);

        long result = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) continue;

            int j = target - i;

            // invalid partner
            if (j < 0 || j >= count.length) return -1;

            // case 1: same number pairing (i == j)
            if (i == j) {
                if (count[i] % 2 != 0) return -1;
                long pairs = count[i] / 2;
                result += pairs * (long)i * i;
                count[i] = 0;
            }
            // case 2: normal pairing
            else {
                if (count[j] != count[i]) return -1;

                long pairs = count[i];
                result += pairs * (long)i * j;

                count[i] = 0;
                count[j] = 0;
            }
        }

        return result;
    }
}