class Solution {
    private void rsort(int[] arr) {
        int max = arr[0];
        for(int a: arr)
            max = Math.max(a, max);
        int[] f = new int[max + 1];
        for(int a: arr)
            f[a]++;
        for(int i = max, j = 0; i >= 0; i--) {
            while(f[i] > 0) {
                arr[j++] = i;
                f[i]--;
            }
        }
    }

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        // int n = nums.length;
        // int[] freq = new int[n + 1];
        // for(int[] r: requests) {
        //     freq[r[0]]++;
        //     freq[r[1] + 1]--;
        // }
        // for(int i = 1; i <= n; i++)
        //     freq[i] += freq[i - 1];
        // // freq = Arrays.stream(freq)
        // //         .boxed()
        // //         .sorted(Collections.reverseOrder())
        // //         .mapToInt(Integer::intValue)
        // //         .toArray();
        // Arrays.sort(freq);

        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // // pq.addAll(Arrays.stream(nums).boxed().toList());
        // for(int num: nums)
        //     pq.offer(num);
        // long res = 0;
        // int mod = 1_000_000_007;
        // // for(int i = 0; i <= n; i++) {
        // //     if(freq[i] == 0)
        // //         break;
        // //     res += (freq[i] * pq.poll());
        // //     res %= mod;
        // // }
        // for(int i = n; i >= 0; i--) {
        //     if(freq[i] == 0)
        //         break;
        //     res += ((long)freq[i] * pq.poll());
        //     res %= mod;
        // }
        // return (int) res;

        int n = nums.length;
        rsort(nums);
        int[] arr = new int[n + 1];
        for(int[] r: requests) {
            arr[r[0]]++;
            arr[r[1] + 1]--;
        }
        for(int i = 1; i <= n; i++)
            arr[i] += arr[i - 1];
        rsort(arr);
        long sum = 0;
        for(int i = 0; i < n; i++) {
            long prod = (long)nums[i] * arr[i];
            if(prod == 0) break;
            sum += prod;
        }
        return (int)(sum % 1_000_000_007);
    }
}