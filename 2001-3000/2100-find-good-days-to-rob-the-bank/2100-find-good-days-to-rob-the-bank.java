class Solution {
    public List<Integer> goodDaysToRobBank(int[] sec, int time) {
        if(time == 0){
            List<Integer> arr = new ArrayList<>();
            for(int i = 0; i < sec.length; i++) arr.add(i);
            return arr;
        }

        List<Integer> arr = new ArrayList<>();
        int pre = 0;
        int suf = 0;
        for(int i = 1; i < sec.length - time; i++){
            if(sec[i - 1] >= sec[i])    pre++;
            else    pre = 0;

            if(sec[i + time - 1] <= sec[i + time])    suf++;
            else    suf = 0;

            if(time <= pre && time <= suf)  arr.add(i);
        }
        return arr;
    }
}