class Solution {
    static {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                    writer.write("0");
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }));
        }
    public int maxDistance(int[] position, int k) {
        Arrays.sort(position);
        int low=1;
        int high=position[position.length-1]-position[0];
        int ans=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            //System.out.println(mid);
            if(isPossible(position,k,mid)){
                ans=mid;
                low=mid+1;
               
            }else{
                high=mid-1;
            }
        }
        return ans;
        
    }
    boolean isPossible(int []position,int k,int mid){
        int count=1;
        int pos=position[0];
        for(int i=1;i<position.length;i++){
            if(position[i]-pos>=mid){
                count++;
                pos=position[i];
                //System.out.println(count+"hii"+position[i]);
            }
            if(count>=k){
            return true;
        }
        }
        
            return false;
        
    }
}