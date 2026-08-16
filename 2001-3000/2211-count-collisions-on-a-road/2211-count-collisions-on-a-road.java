class Solution {
    public int countCollisions(String str) {
        int count=0;
        int left=0,right=str.length()-1;
        char[] s=str.toCharArray();
        while(left<s.length && s[left]=='L'){
            left++;
        }
        while(right>=0 && s[right]=='R'){
            right--;
        }
        for(int i=left;i<=right;i++){
            if(s[i]!='S') count++;
        }
        return count;
    }
}