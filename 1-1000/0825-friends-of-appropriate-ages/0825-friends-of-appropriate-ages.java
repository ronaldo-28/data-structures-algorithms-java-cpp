class Solution {
    public int numFriendRequests(int[] ages) {
        int[] ageCount = new int[121];
        int[] sumCount = new int[121];
        for(int i=0;i<ages.length;i++){
            ageCount[ages[i]]++;
        }
        sumCount[0] = ageCount[0];
        for(int i=1;i<=120;i++){
            sumCount[i] = ageCount[i]+sumCount[i-1];
        }

        int res = 0;
        for(int i=15;i<=120;i++){
            if(ageCount[i]==0) continue;
            int count = sumCount[i]-sumCount[i/2+7];
            res += ageCount[i]*count-ageCount[i];
        }
        return res;
    }
}