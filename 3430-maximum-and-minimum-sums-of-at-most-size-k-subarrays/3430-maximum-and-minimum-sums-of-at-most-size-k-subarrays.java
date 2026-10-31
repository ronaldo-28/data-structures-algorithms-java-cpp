class Solution {
    public long count(long l, long r, long k) {
    long m = Math.min(r, k);
    long a = Math.min(l, k);

    if (m <= k - a + 1) {
        return m * a;
    }

    long first = k - a + 1;

    long ans = first * a;

    long remaining = m - first;

    ans += remaining * (2 * a - remaining - 1) / 2;

    return ans;
}
    public long minMaxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prevg = new int[n];
        int[] nextg = new int[n];
        int[] prevs = new int[n];
        int[] nexts = new int[n];
        int[] great = new int[n];
        int[] small = new int[n];
        int tg =-1;
        int ts =-1;


        for(int i=0;i<n;i++){
            while (tg!=-1 && nums[great[tg]]<=nums[i]){
                tg--;
            }
            if (tg==-1){
                prevg[i]=-1;
            }
            else{
                prevg[i]=great[tg];

            }
            great[++tg]=i;

            while (ts!=-1 && nums[small[ts]]>=nums[i]){
                ts--;
            }
            if (ts==-1){
                prevs[i]=-1;
            }
            else{
                prevs[i]=small[ts];

            }
            small[++ts]=i;
            


        }
ts = -1;
tg = -1;

for (int i = n - 1; i >= 0; i--) {

    // next greater
    while (tg != -1 && nums[great[tg]] < nums[i]) {
        tg--;
    }

    if (tg == -1) {
        nextg[i] = n;
    } else {
        nextg[i] = great[tg];
    }

    great[++tg] = i;


    // next smaller
    while (ts != -1 && nums[small[ts]] > nums[i]) {
        ts--;
    }

    if (ts == -1) {
        nexts[i] = n;
    } else {
        nexts[i] = small[ts];
    }

    small[++ts] = i;
}


        
        long answer=0;
        long l=0;
        long r=0;
        for(int i =0;i<n;i++){
            l= i - prevg[i];
            r=nextg[i]-i;
            long maxcount = count(l,r,k);
            answer +=nums[i]*maxcount;
            
            l= i - prevs[i];
            r=nexts[i]-i;
            long mincount = count(l,r,k);
            answer +=nums[i]*mincount;
            
                    }
        return answer;

    }
}