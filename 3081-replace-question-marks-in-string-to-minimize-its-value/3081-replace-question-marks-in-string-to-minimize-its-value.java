class Solution {
    public String minimizeStringValue(String s) {
        char[] arr = s.toCharArray();
        int[] freq = new int['z'+1];
        int sum = 0;
        for ( char ch : arr ) {
            if ( ch == '?' ) continue;
            freq[ch]++;
            sum++;
        }
        if ( sum == arr.length ) return s;

        
        int[] freqSorted = new int[26];
        for ( int ii = 0; ii < 26; ii++ ) freqSorted[ii] = freq[ii+'a'];

        Arrays.sort(freqSorted);

        int total = arr.length;
        int cnt = 26;
        while (cnt > 0 && freqSorted[cnt-1] >= 1 + (total-1) / cnt) {
            total -= freqSorted[cnt-1];
            cnt--;
        }
// System.out.printf("total=%d, cnt=%d\n", total, cnt);
        int div = total / cnt;
        int mod = total % cnt;
        int idx = 0;
// System.out.printf("div=%d, mod=%d, freq=%s\n", div, mod, Arrays.toString(Arrays.copyOfRange(freq, 'a', 'z'+1)));
        for ( char ch = 'a'; ch <= 'z'; ch++ ) {
            int limit = div + (mod > 0 ? 1 : 0) - freq[ch];
            if ( limit <= 0 ) continue;
            mod--;
// System.out.printf("ch=%c, limit=%d\n", ch, limit);
            while (idx < arr.length && limit > 0) {
                if ( arr[idx] == '?' ) {
                    arr[idx] = ch;
                    limit--;
                }
                idx++;
            }
            if ( idx >= arr.length ) break;
        }
        return new String(arr);
    }
}