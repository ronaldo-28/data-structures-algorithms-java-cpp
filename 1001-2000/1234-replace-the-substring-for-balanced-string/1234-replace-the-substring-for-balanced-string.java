class Solution {
    public int balancedString(String s) {
        char[] charArr = s.toCharArray();
       int[] arr = new int[122];
       for(int i=0;i<charArr.length;i++){
           arr[charArr[i]]++;
       } 

       int n = s.length();

       int k = s.length()/4;
       //already balanced
       if(arr['Q']==k&&arr['W']==k&&arr['E']==k&&arr['R']==k){
          return 0;
       }

       int l =0,r=0;
       int minLength = n;
       while(r<charArr.length){
            arr[charArr[r]]--;
            //I am looking outside the window
            while(l<n&&arr['Q']<=k&&arr['W']<=k&&arr['E']<=k&&arr['R']<=k){
                 minLength = Math.min(minLength,r-l+1);
                 arr[charArr[l]]++;
                 l++;
            }

            r++;
       }

       return minLength;
    }
}