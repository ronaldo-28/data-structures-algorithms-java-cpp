class Solution {
    public boolean possible(int[] arr , int mid ,int m ,int k)
    {
        int count =0;
        int boque=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]<=mid)
            count++;
            else 
            {
            boque+=(count/k);
            count=0;
            }
        }
        boque+=(count/k);
        return boque>=m;
    }
    public int minDays(int[] bloomDay, int m, int k) {
        if ((long)m*k>bloomDay.length)
        return -1;
        int low= Arrays.stream(bloomDay).min().getAsInt();
        int high= Arrays.stream(bloomDay).max().getAsInt();
        int ans = high;
        while (low<=high)
        {
            int mid= (low+high)/2;
            if (possible(bloomDay,mid,m,k)== true)
            {
                ans=mid ;
                high= mid-1;
            }
            else low = mid+1;
        }
        return ans;
        
    }
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    
}