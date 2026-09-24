class Solution {
    /*
    public int minimumTimeToInitialState(String word, int k) {
        char[] ch = word.toCharArray();
        int n = ch.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        
        for(int i = 1; i < n; i++) {
            if(i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while(i + z[i] < n && ch[i + z[i]] == ch[z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
            if(i % k == 0 && z[i] == n - i) return i / k;
        }
        return (n + k - 1) / k;
    }
    */
    public int minimumTimeToInitialState(String word, int k) {
        char[] ch = word.toCharArray();
        int[] kmp = helper(ch);
        int n = ch.length;
        int len = kmp[n - 1];
        while(len > 0 && (n - len) % k != 0) len = kmp[len - 1];
        if(len == 0) return (n + k - 1) / k;
        else return (n - len) / k;
    }
    
    int[] helper(char[] ch) {
        int n = ch.length;
        int[] res = new int[n];
        int index = 0;

        for(int i = 1; i < n;) {
            if(ch[i] == ch[index]) {
                res[i] = index + 1;
                i++;
                index++;
            } else {
                if(index != 0) index = res[index - 1];
                else i++;
            }
        }
        
        return res;
    }
    
    
    /*
    public int minimumTimeToInitialState(String word, int k) {
        char[] ch = word.toCharArray();
        int n = ch.length;
        
        int[] sa = new int[n], rank = new int[n];
        int[] ssa = new int[n], old_rank = new int[n];
        int[] count = new int[Math.max(256, n)], height = new int[n];
        
        for(int i = 0; i < n; i++) count[rank[i] = ch[i]]++;
        for(int i = 1; i < count.length; i++) count[i] += count[i - 1];
        for(int i = n - 1; i >= 0; i--) sa[--count[rank[i]]] = i;
        
        for(int len = 1, m = count.length, p = 0; len <= n; len *= 2, m = p + 1, p = 0) {
            for(int i = n - len; i < n; i++) ssa[p++] = i;
            for(int i = 0; i < n; i++) if(sa[i] >= len) ssa[p++] = sa[i] - len;
            
            Arrays.fill(count, 0);
            for(int i = 0; i < n; i++) count[rank[ssa[i]]]++;
            for(int i = 1; i < m; i++) count[i] += count[i - 1];
            for(int i = n - 1; i >= 0; i--) sa[--count[rank[ssa[i]]]] = ssa[i];
            
            old_rank = Arrays.copyOf(rank, n);
            p = 0;
            rank[sa[0]] = 0;
            
            for(int i = 1; i < n; i++) {
                if(old_rank[sa[i]] != old_rank[sa[i - 1]]) rank[sa[i]] = ++p;
                else if(sa[i - 1] + len >= n) {
                    if(sa[i] + len < n) rank[sa[i]] = ++p;
                    else rank[sa[i]] = p;
                }else if(old_rank[sa[i] + len] != old_rank[sa[i - 1] + len]) rank[sa[i]] = ++p;
                else rank[sa[i]] = p;
            }
        }
        
        int cur = 0, max = 0;
        for(int i = 0; i < n; i++) {
            if(cur >= 1) cur--;
            if(rank[i] == 0) continue;
            int j = sa[rank[i] - 1];
            while(i + cur < n && j + cur < n && ch[i + cur] == ch[j + cur]) cur++;
            height[rank[i]] = cur;
            max = Math.max(max, cur);
        }
        
        // System.out.println("rank[0]: " + rank[0]);
        // System.out.println("rank: " + Arrays.toString(rank));
        // System.out.println("sa: " + Arrays.toString(sa));
        // System.out.println("height: " + Arrays.toString(height));
        
        int pivot = rank[0], prev = height[pivot];
        int res = (n + k - 1) / k;
        for(int i = pivot - 1; i >= 0; i--) {
            if(prev == 0) break;
            int start = sa[i];
            if(start % k == 0 && start + prev == ch.length) {
                res = Math.min(res, start / k);
            }
            prev = Math.min(prev, height[i]);
        }
        
        Integer h = null;
        for(int i = pivot + 1; i < ch.length; i++) {
            if(h == null) h = height[i];
            else h = Math.min(h, height[i]);
            if(h == 0) break;
            int start = sa[i];
            if(start % k == 0 && start + h == ch.length) {
                res = Math.min(res, start / k);
            }
        }
        
        
        return res;
    }
    */
}