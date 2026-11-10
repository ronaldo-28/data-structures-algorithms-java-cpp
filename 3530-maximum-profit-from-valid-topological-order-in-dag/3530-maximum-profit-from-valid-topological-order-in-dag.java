class Solution {
    HashMap<String,Integer> dp;
    int lmt;
    public int rec(HashMap<Integer,ArrayList<Integer>> map,int bit,int idx,int indeg[],int score[]){
        
            if(bit==lmt){
            return 0;}
            
            String key=bit+" "+idx;
            if(dp.containsKey(key)){
                return dp.get(key);
            }
        int ans=0;
        int min=Integer.MAX_VALUE;
        int k=-1;
        for(int i=0;i<map.size();i++){
            int b=(int)((bit>>i)&1);
            if(indeg[i]==0 && b==0){
                if(min>score[i] && map.get(i).size()==0){
                    min=score[i];
                    k=i;
                }
            }}
            for(int i=0;i<map.size();i++){
                int b=(int)((bit>>i)&1);
                if(b==0 && indeg[i]==0 && map.get(i).size()>0){
                    for(int j:map.get(i)){
                indeg[j]--;}
                    int bi=(bit)|(1<<i);
                    // System.out.println(rec(map,bi,idx+1,indeg,score)+" "+((idx+1)*score[i])+" "+i);
                    ans=Math.max(rec(map,bi,idx+1,indeg,score)+((idx+1)*score[i]),ans);
                    for(int j:map.get(i)){
                indeg[j]++;}
                    
                }
            }
        if(k!=-1){
         int bi=(bit)|(1<<k);
                    // System.out.println(rec(map,bi,idx+1,indeg,score)+" "+((idx+1)*score[i])+" "+i);
                    ans=Math.max(rec(map,bi,idx+1,indeg,score)+((idx+1)*score[k]),ans);}
        
            dp.put(key,ans);
            return ans;

    }
    public int maxProfit(int n, int[][] edges, int[] score) {
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(i,new ArrayList<Integer>());
        }
        int indeg[]=new int[n];
        for(int e[]:edges){
            map.get(e[0]).add(e[1]);
            indeg[e[1]]++;
        }
        lmt=(int)(Math.pow(2,n)-1);
        dp=new HashMap<>();
        return rec(map,0,0,indeg,score);
        
    }
}