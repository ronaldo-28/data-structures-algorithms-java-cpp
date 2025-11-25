class Solution {
    public boolean isSubsequence(String s, String t) {
        
        if (s.isEmpty()){
            return true;
        }else if (!s.isEmpty() && t.isEmpty()){
            return false;
        }
        boolean firstRun = true;
        int prevPos = 0;
        int n = 0;
        String searchRoom = t;
        for (int i = 0; i < t.length(); i++){

            char c = s.charAt(n);
            int pos = searchRoom.indexOf(c);
            
            if (pos == -1){
                if (searchRoom.isEmpty()){
                    return false;
                }
                searchRoom = searchRoom.substring(1);
                return false;
            }else{
                i = pos + 1;
                searchRoom = searchRoom.substring(i);
                n++;
                if (n >= s.length()){
                    return true;
                }
                if (i >= t.length()){
                    return false;
                }
            }
        }

        return false;
    }
}