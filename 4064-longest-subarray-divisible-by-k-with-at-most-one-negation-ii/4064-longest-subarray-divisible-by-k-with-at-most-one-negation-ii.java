class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        int remain = 0;
        int cnt = 1;
        int ans = 0;
        int[] prefix = new int[k];
        int[] suffix = new int[k];
        int[] temp = new int[k];
        int[]head = new int[k];
        int [] tail = new int[n];
        for(int i=0;i<k;i++){
            if(i==0) {
                prefix[i]=0;
            } else {
                prefix[i]=-1;
            }
            head[i] = -1;
        }

        for(int i=0;i<n;i++){
            remain = Math.floorMod(remain+nums[i],k);
            suffix[remain]=i+1;
            if(prefix[remain]==-1){
                prefix[remain]=(i+1);
                temp[cnt] = remain;
                cnt++;
            }
            ans = Math.max(ans, i+1-prefix[remain]);
        }

        if(ans==n){
            return n;
        }

        for(int i=n-1;i>=0;i--){
            int temp1 = Math.floorMod(2*nums[i],k);
            tail[i]=head[temp1];
            head[temp1]=i;
        }

        for(int i=1;i<k;i++){
            int temp2 = head[i];
            if(temp2!=-1) {
                for(int j=0;j<cnt;j++){
                    if (n-prefix[temp[j]]<=ans) {
                        break;
                    }

                    
                    while(temp2!=-1 && temp2 < prefix[temp[j]]) {
                        temp2 = tail[temp2];
                    }
                    if(temp2==-1){
                        break;
                    }

                    if (suffix[(temp[j]+i)%k] > temp2) {
                        ans = Math.max(ans, suffix[(temp[j]+i)%k] - prefix[temp[j]]);
                        if(ans==n){
                            return n;
                        }
                    }
                }
            }
        }
        return ans;
    }
        
}