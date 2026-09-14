class Solution {
    public int maxHappyGroups(int batchSize, int[] groups) {
        if (batchSize == 1) return groups.length;
        int[] withSize = new int[batchSize];
        for (int size : groups) withSize[size % batchSize]++;
        int fromZero = withSize[0];
        withSize[0] = 0;
        int fromEnds = 0;
        for (int l = 1, r = batchSize - 1; l < r; l++, r--) {
            int usable = Math.min(withSize[l], withSize[r]);
            fromEnds += usable;
            withSize[l] -= usable; withSize[r] -= usable;
        }
        int fromMid = 0;
        if (batchSize % 2 == 0) {
            fromMid = withSize[batchSize / 2] / 2;
            withSize[batchSize / 2] -= fromMid * 2;
        }
        return get(pruneEnd(withSize), batchSize, 0, new HashMap<>()) + fromZero + fromEnds + fromMid;
    }
    private int get(int[] ar, int batchSize, int rem, Map<Long, Integer> cache) {
        long hash = 0;
        for (int e : ar) hash = hash * 69l + e;
        Integer fromCache = cache.get(hash);
        if (fromCache != null) return fromCache;
        if (zeroed(ar)) {
            cache.put(hash, 0);
            return 0;
        }
        int max = 0;
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == 0) continue;
            ar[i]--;
            int from = get(ar, batchSize, (rem + i) % batchSize, cache);
            if (from > max) max = from;
            ar[i]++; 
        }
        // System.out.println(Arrays.toString(ar)+", "+rem+": "+max);
        int score = max + (rem == 0 ? 1 : 0);
        cache.put(hash, score);
        return score;
    }
    private int[] pruneEnd(int[] in) {
        int endingZeros = 0;
        for (int i = in.length - 1; i >= 0; i--) {
            if (in[i] != 0) break;
            endingZeros++;
        }
        int[] out = new int[in.length - endingZeros];
        for (int i = 0; i < out.length; i++) out[i] = in[i];
        return out;
    }
    private boolean zeroed(int[] ar) {
        for (int e : ar) if (e != 0) return false;
        return true;
    }
}
// old attempt x2
// class Solution {
//     public int maxHappyGroups(int batchSize, int[] groups) {
//         if (batchSize == 1) return groups.length;
//         int[] withSize = new int[batchSize];
//         for (int size : groups) withSize[size % batchSize]++;
//         int nHappyGroups = withSize[0];
//         for (int aSize = 1, bSize = batchSize - 1; aSize < bSize; aSize++, bSize--) {
//             int canUse = Math.min(withSize[aSize], withSize[bSize]);
//             nHappyGroups += canUse;
//             withSize[aSize] -= canUse;
//             withSize[bSize] -= canUse;
//         }
//         if (batchSize % 2 == 0) {
//             int midGroupCount = withSize[batchSize / 2];
//             nHappyGroups += midGroupCount / 2;
//             withSize[batchSize / 2] = midGroupCount % 2;
//         }
//         System.out.println(Arrays.toString(withSize));
//         List<Integer> initialState = new ArrayList<>();
//         for (int amt : withSize) initialState.add(amt);
//         System.out.println(initialState);
//         return nHappyGroups;
//     }
//     private int get(List<Integer> state) {
//         int sum = 0;
//         for (int amt : state) sum += amt;
//         if (sum == 0) return 0;
//         return 1;
//     }
// }
// class Solution {
//     public int maxHappyGroups(int batchSize, int[] groups) {
//         int[] freq = new int[batchSize];
//         for (int group : groups) freq[group % batchSize]++;
//         System.out.println("Initial freq: "+Arrays.toString(freq));
//         int nHappy = freq[0]; freq[0] = 0;
//         for (int l = 1, r = batchSize - 1; l < r; l++, r--) {
//             int amt = (int) Math.min(freq[l], freq[r]);
//             nHappy += amt;
//             freq[l] -= amt; freq[r] -= amt;
//         }
//         if (batchSize % 2 == 0) {
//             nHappy += freq[batchSize / 2] / 2;
//             freq[batchSize / 2] &= 1;
//         }
//         TreeMap<Integer, List<Integer>> usable = new TreeMap<>();
//         for (int i = 2; i < (1 << batchSize); i += 2) {
//             int sum = sum(i, batchSize);
//             if (sum % batchSize == 0) 
//                 usable.computeIfAbsent(sum, nothing -> new ArrayList<>()).add(i);
//         }
//         BigLoop:
//         for (Map.Entry<Integer, List<Integer>> group : usable.entrySet()) {
//             int sum = group.getKey(); List<Integer> combos = group.getValue();
//             for (int combo : combos) {
//                 int amt = Integer.MAX_VALUE;
//                 for (int shift = 1; shift < batchSize; shift++) {
//                     if ((combo & (1 << shift)) != 0) {
//                         if (amt > freq[shift]) amt = freq[shift];
//                     }
//                 }
//                 if (amt == 0) continue;
//                 nHappy += amt;
//                 for (int shift = 1; shift < batchSize; shift++) {
//                     if ((combo & (1 << shift)) != 0) {
//                         freq[shift] -= amt;
//                     }
//                 }
//             }
//             for (int amt : freq) {
//                 if (amt != 0) continue BigLoop;
//             }
//             break;
//         }
//         System.out.println("Easy combos exhausted: "+Arrays.toString(freq)+".");
//         int totalSum = 0;
//         for (int i = 0; i < batchSize; i++) totalSum += i * freq[i];
//         if (totalSum == 0) return nHappy;
//         for (int sum = batchSize; sum <= totalSum; sum += batchSize) {
            
//         }
//     }
//     private int sum(int combo, int size) {
//         int sum = 0;
//         for (int shift = 1; shift < size; shift++) if ((combo & (1 << shift)) != 0) sum += shift;
//         return sum;
//     }
// }

/* old attempt
int[] counts = new int[batchSize + 1];
        int[] specials = new int[batchSize + 1];
        for (int groupSize : groups) {
            if (groupSize <= batchSize) specials[groupSize % batchSize]++;
            else counts[groupSize % batchSize]++;
        }
        int nHappy = specials[0];
        int max_i = batchSize / 2;
        for (int i = 1; i < max_i; i++) {
            int min = (int) Math.min(specials[i], specials[batchSize - i]);
            specials[i] -= min; specials[batchSize - i] -= min;
            nHappy += 2 * min;
            if (specials[i] == 0) continue;
            int combo = (int) Math.min(specials[i], counts[batchSize - i]);
            int combo2 = (int) Math.min(specials[batchSize - i], counts[i]);
            nHappy += combo + combo[2];
            counts[batchSize - i] -= combo;
            counts[i] -= combo;
        }
        if ((batchSize & 1) == 0) {
            int amt = specials[max_i] - (specials[max_i] % 2);
            specials[max_i] -= amt;
            nHappy += amt;
            if (specials[max_i] > 0 && counts[max_i] > 0) {
                nHappy += 2;
                counts[max_i]--;
            }
            nHappy += counts[max_i] / 2;
        }
        System.out.println(nHappy);
        return nHappy;
*/