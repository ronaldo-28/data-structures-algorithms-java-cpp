// class Solution {
//     public int maxLengthBetweenEqualCharacters(String s) {
//         int maxLen = -1;
//         int[] firstIndex = new int[26];
//         Arrays.fill(firstIndex, -1);
        
//         for (int i = 0; i < s.length(); i++) {
//             int idx = s.charAt(i) - 'a';
//             if (firstIndex[idx] == -1) {
//                 firstIndex[idx] = i;
//             } else {
//                 maxLen = Math.max(maxLen, i - firstIndex[idx] - 1);
//             }
//         }
        
//         return maxLen;
//     }
// }


class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int maxLen = -1;
        
        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            int last = s.lastIndexOf(c);
            if (first != -1 && last != first) {
                maxLen = Math.max(maxLen, last - first - 1);
            }
        }
        
        return maxLen;
    }
}
// class Solution {
//     public int maxLengthBetweenEqualCharacters(String s) {
        
//         int[] firstindex = new int[26] ;


//         for(int i = 0 ; i< 26; i++){


//             firstindex[i] = -1;
//         }

//         int maxLen = -1;

//         for(int i = 0 ; i< s.length(); i++){

//             int indx = s.charAt(i) - 'a';

//             if(firstindex[indx] == -1){

//                 firstindex[indx] = i;
//             }

//             else{


//                 int len  = i - firstindex[indx] -1;

//                 if(len> maxLen){

//                     maxLen = len;
//                 }
//             }
//         }

//         return maxLen;
//     }
// }