class Solution {
    public long[] minimumCosts(int[] regular, int[] express, int expressCost) {
        long[] answer = new long[regular.length];
        long expressTotal = expressCost, prevCost = 0, regularTotal;
        for(int i = 0; i < answer.length; i++)
        {
            regularTotal = prevCost + regular[i];
            expressTotal = Math.min(express[i] + expressTotal,  regularTotal + expressCost);
            answer[i] = Math.min(expressTotal, regularTotal);
            prevCost = answer[i];
        }
        return answer;
    }
}