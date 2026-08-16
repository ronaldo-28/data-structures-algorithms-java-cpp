class Solution {
    public int solve(List<Integer> nums)
    {
        int n=nums.size();
        int a=1, b=(int)(Math.pow(2, nums.get(0)));
        for(int i=1;i<n;i++)
        {
            int c=b;
            c+=(int)(a*(Math.pow(2, nums.get(i))-1));
            a=b;
            b=c;
        }
        return b;

    }
    public int beautifulSubsets(int[] nums, int k) {
        int n=nums.length;
        if(n==1)
        {
            return 1;
        }
        HashMap<Integer, Integer> h=new HashMap<>();
        for(int i=0;i<n;i++)
        {
            h.put(nums[i], h.getOrDefault(nums[i], 0)+1);
        }
        int res=1;
        for(int i=0;i<n;i++)
        {
            int num=nums[i];
            if(h.containsKey(num)==false)
            {
                continue;
            }
            while(h.containsKey(num-k))
            {
                num-=k;
            }
            List<Integer> temp=new ArrayList<Integer>();
            while(h.containsKey(num))
            {
                temp.add(h.get(num));
                h.remove(num);
                num+=k;
            }
            res*=solve(temp);
        }
        return res-1;
    }
}