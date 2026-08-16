class Solution {
    public int minimumCardPickup(int[] cards) {
        int max=0;
        for(int i=0;i<cards.length;i++){
            if(max<cards[i])    max=cards[i];
        }
        int result=Integer.MAX_VALUE;
        int j=0;
        boolean[] bl=new boolean[max+1];
        for(int i=0;i<cards.length;i++){
            while(bl[cards[i]]){
                if(result>i-j+1){
                    result=i-j+1;
                }
                bl[cards[j]]=false;
                j++;
            }
            bl[cards[i]]=true;
        }
        return (result)==Integer.MAX_VALUE?-1:result;
    }
}

    // HashMap<Integer, Integer> map = new HashMap<>();
    //     int min = Integer.MAX_VALUE;
    //     for (int i = 0; i < cards.length; i++) {
    //         if (map.containsKey(cards[i])) {
    //             int len = i - map.get(cards[i]) + 1;
    //             min = Math.min(min, len);
    //         }
    //         map.put(cards[i], i);
    //     }
    //     return min == Integer.MAX_VALUE ? -1 : min;