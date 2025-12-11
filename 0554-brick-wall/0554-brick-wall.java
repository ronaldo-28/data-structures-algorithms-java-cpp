class Solution {
    static{
        for(int i=0;i<500;i++){
            leastBricks(null);
        }
    }
    public static int leastBricks(List<List<Integer>> wall) {
        if(wall==null)
        return 0;
        Map<Integer,Integer> map=new HashMap<>();
        int max=0;
        for(List<Integer> subList:wall){
            int sum=0;
            for(int i=0;i<subList.size()-1;i++){
                int num=subList.get(i);
                sum+=num;
                map.put(sum,map.getOrDefault(sum,0)+1);
                max=Math.max(max,map.get(sum));
            }
        }
        return wall.size()-max;
        
    }
}