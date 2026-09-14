class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] degrees = new int[n];

        for (int[]road : roads) {
            degrees[road[0]]++;
            degrees[road[1]]++;
        }

        int firstMax = 0, secondMax = 0;
        for(int degree: degrees) {
            if (degree > firstMax) {
                secondMax = firstMax;
                firstMax = degree;
            } else if (degree < firstMax && degree > secondMax) {
                secondMax = degree;
            }
        }

        int firstCount = 0, secondCount = 0;
        for (int degree : degrees) {
            if (degree == firstMax) firstCount++;
            if (degree == secondMax) secondCount++;
        }

        // there are many cities that have first max degree
        if (firstCount > 1) {
            int totalPairs = firstCount * (firstCount - 1) / 2;

            int edgeCount = 0;
            for(int[] road:roads) {
                if (degrees[road[0]] == firstMax && degrees[road[1]] == firstMax) edgeCount++;
            }

            return 2 * firstMax - (edgeCount == totalPairs ? 1 : 0);
        }
        // there is only one city with first max degree
        // and there are many cities that have second max degree
        else {
            int edgeCount = 0;
            for(int[] road:roads) {
                if (degrees[road[0]] == firstMax && degrees[road[1]] == secondMax) edgeCount++;
                if (degrees[road[0]] == secondMax && degrees[road[1]] == firstMax) edgeCount++;
            }
            return firstMax + secondMax - (edgeCount == secondCount ? 1 : 0);
        }
    }
}