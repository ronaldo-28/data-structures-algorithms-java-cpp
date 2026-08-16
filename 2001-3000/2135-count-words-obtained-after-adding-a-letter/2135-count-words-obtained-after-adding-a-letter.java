class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {

        // Approach 2
        Set<Integer> set = new HashSet<>();
        for(String word : startWords) {
            int mask = 0;
            for(int i=0; i<word.length(); i++) {
                mask = mask | (1 << (word.charAt(i) - 'a'));
            }
            set.add(mask);
        }

        int count = 0;
        for(String tWord : targetWords) {
            int mask = 0;
            for(int i=0; i<tWord.length(); i++) {
                mask |= (1 << (tWord.charAt(i) - 'a'));
            }

            for(int i=0; i<tWord.length(); i++) {
                int newMask = mask ^ (1 << (tWord.charAt(i) - 'a')); // remove mask of char one by one by doing x-or(^)
                if(set.contains(newMask)) {
                    count++;
                    break;
                }
            }
        }

        return count;


      //Approach 1
      /**
        Time Complexity :- O(n* klog(k) + m * (k^2 log(k)))
       */
    //   Set<String> set = new HashSet<>();
    //   for(String word : startWords) {
    //     char arr[] = word.toCharArray();
    //     Arrays.sort(arr);
    //     set.add(new String(arr));
    //   }

    //   int count = 0;

    //   for(String tWord : targetWords) {
    //     for(int i=0; i<tWord.length(); i++) {
    //        String substr = tWord.substring(0, i) + tWord.substring(i+1);
    //        char arr[] = substr.toCharArray();
    //        Arrays.sort(arr);
    //        if(set.contains(new String(arr))) {
    //             count++;
    //             break;      
    //        }
    //     }
    //   }

    //   return count;
    }
}