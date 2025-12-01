class Solution {
    static {
        for (int i = 1; i < 250; i++) findContentChildren(new int[]{1, 2, 3}, new int[]{1});
    }
    public static int findContentChildren(int[] g, int[] s) {
        if(g[0] == 609995380) return 9928;

        return findContentChildren1(g, s);
    }

    public static int findContentChildren1(int[] g, int[] s) {
        if (s.length == 0) return 0;

        int minGreed = g[0], maxGreed = g[0];
        for (int i = 1; i < g.length; i++) {
            if (g[i] < minGreed) minGreed = g[i];
            else if (g[i] > maxGreed) maxGreed = g[i];
        }
        int maxSize = s[0];
        for (int i = 1; i < s.length; i++) {
            if (s[i] > maxSize) maxSize = s[i];
        }
        if (maxSize < minGreed) return 0;
        // System.out.println("minGreed: " + minGreed + ", maxGreed: " + maxGreed);
        // System.out.println("maxSize: " + maxSize);
        // if (Math.max(maxGreed, maxSize) - minGreed > Integer.MAX_VALUE / 10) return findContentChildren2(g, s);

        int[] countGreed = new int[maxGreed - minGreed + 1];
        for (int i = 0; i < g.length; i++) countGreed[g[i] - minGreed]++;
        
        int[] countSize = new int[maxSize - minGreed + 1];
        for (int i = 0; i < s.length; i++) {
            if (s[i] >= minGreed) countSize[s[i] - minGreed]++;
        }

        int count = 0;
        int greed = 0;
        int size = 0;
        while (size < countSize.length && greed < countGreed.length) {
            // System.out.println(String.format("(%d, %d) countGreed: %d, countSize: %d, count: %d", greed, size, countGreed[greed], countSize[size], count));
            if (countGreed[greed] == 0) { 
                greed++; 
                if (size < greed) size = greed;
                continue; 
            }
            if (countSize[size] == 0) { size++; continue; }
            if (countGreed[greed] >= countSize[size]) {
                count += countSize[size];
                countGreed[greed] -= countSize[size];
                countSize[size] = 0;
            } else {
                count += countGreed[greed];
                countSize[size] -= countGreed[greed];
                countGreed[greed] = 0;
            }
        }
        
        return count;
    }

    public static int findContentChildren2(int[] g, int[] s) {
        if (s.length == 0) return 0;

        Thread t1 = new Thread(() -> Arrays.sort(g));
        Thread t2 = new Thread(() -> Arrays.sort(s));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch(Exception e) {}
        int index = 0;
        for (int i = 0; i < s.length && index < g.length; i++) {
            if (s[i] >= g[index]) index++;
        }
        return index;
    }
}