class FindSumPairs 
{
    public int[] nums1;
    public int[] nums2;
    public int[] freqArr;
    public HashMap<Integer, Integer> extraMap;
    public final int M = 200_000;

    // Constructor: FindSumPairs(nums1, nums2)
    public FindSumPairs(int[] nums1, int[] nums2) 
    {
        // Step 1: Save both arrays into the object
        this.nums1 = nums1;
        this.nums2 = nums2;

        // Step 2: Initialize freqArr and extraMap
        freqArr = new int[M + 1];
        extraMap = new HashMap<>();

        // Step 3: Build frequency data from nums2
        for (int v : nums2) 
        {
            if (v <= M) 
            {
                freqArr[v]++; // If v ≤ 200000
            } 
            else 
            {
                extraMap.put(v, extraMap.getOrDefault(v, 0) + 1); // If v > 200000
            }
        }
    }

    public void add(int index, int val) 
    {
        // Step 1: Get the old value from nums2[index]
        int old = nums2[index];

        // Step 2: Decrease count of old value
        if (old <= M)
        {
            freqArr[old]--;
        } 
        else 
        {
            int c = extraMap.get(old) - 1;
            if (c == 0)
            {
                extraMap.remove(old);
            } 
            else
            {
                extraMap.put(old, c);
            } 
        }

        // Step 3: Compute new value
        int nw = old + val;
        
        // Step 4: Update nums2[index]
        nums2[index] = nw; 

        // Step 5: Increase count of new value
        if (nw <= M)
        {
            freqArr[nw]++;
        } 
        else
        {
            extraMap.put(nw, extraMap.getOrDefault(nw, 0) + 1);
        } 
    }

    public int count(int tot) 
    {
        // Step 1: Initialize answer
        int ans = 0; 

        // Step 2: Loop through nums1
        for (int a : nums1) 
        { 
            // Step 3: Compute b = tot - a
            int b = tot - a;  

            // Step 4: Check for b in freqArr or extraMap
            if (b >= 0 && b <= M) 
            {
                ans += freqArr[b];
            } 
            else 
            {
                ans += extraMap.getOrDefault(b, 0);
            }
        }

        // Step 5: Return answer
        return ans; 
    }
}