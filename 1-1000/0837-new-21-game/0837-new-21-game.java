class Solution {
    public double new21Game(int n, int k, int maxPts) {
        /**
        难点： 如果一个区间内， 拿到每一个数的概率是一样的
        那么当前拿到每一个数的实际概率是： 这个区间的概率和/这个区间的长度 --》因为每一个数的贡献概率是一样的

        所以这个题 使用 sliding window 去维持一个区间， 然后计算这个区间的概率和. 区间长度就是maxPts
        区间 [i-1]....[i-maxPts] -->i就是从这个区间出来的
         */
         //dp[i]-->拿到数i的概率
        double[] dp = new double[n+1];
        dp[0] = 1.0;
        double windowsum = 1.0;
        for(int i = 1; i<=n; i++){
            //当前这个区间的概率和是windowSum， 除以区间长度， 答案就是当前随机拿这个区间数的概率
            double p = windowsum / maxPts;
            //如果当前p已经是不可能的， 所以直接退出
            if(p == 0.0) break;
            dp[i] = p;

            //说明要继续往后
            if(i<k){
                windowsum += p;
            }

            //说明当前数i已经超过原来的区间了， 所以要把区间往前移动一位
            if(i-maxPts >= 0){
                windowsum -= dp[i-maxPts];
            }
            
        }

        double res = 0.0;
        for(int i = k; i<=n; i++){
            res += dp[i];
        }
        //如果超过1.0， 最后概率还是1.0
        return res>1.0?1.0:res;

        
        

    }
}