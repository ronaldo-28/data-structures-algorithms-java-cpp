class Solution {
    public int findLUSlength(String[] strs) {

        int n = strs.length;
        int result = -1;

        for(int i = 0; i < n; ++i){
            if(strs[i].length() <= result) continue;
            
            boolean isCommon = false;
            for(int j = 0; j < n; ++j){
                if(j == i) continue;
                isCommon = isSubsequence(strs[i], strs[j]);
                if(isCommon) break;
            }

            if(!isCommon)
                result = strs[i].length();
             
        }
        
        return result;
    }

    boolean isSubsequence(String a, String b){

        int i = 0;
        for(int j = 0; i < a.length() && j < b.length(); ++j){
            if(a.charAt(i) == b.charAt(j))
                i++;
        }
        
        return i == a.length();
    }
}