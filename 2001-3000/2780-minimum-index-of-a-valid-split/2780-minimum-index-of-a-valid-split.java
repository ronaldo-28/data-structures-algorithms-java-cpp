class Solution {
    public int minimumIndex(List<Integer> nums) {
        int max=nums.get(0);
         int n=nums.size();

        int c=0; //c stands for count
        for(int i=0;i<n;i++){
            if(c==0){max=nums.get(i);c=1;}
            else if(max!=nums.get(i)){c--;}
            else{c++;}
        }

        
        c=0;
         for(int i=0;i<n;i++){
            if(nums.get(i)==max){c++;}
         }
         int totalCount=c;

         c=0;
         for(int i=0;i<n-1;i++){
            //after splitting, check for sizes of array
            if(nums.get(i)==max){c++;} //here c - is for current count of that dominant element
         
            int split1=(i+1);
            int split2=(n-split1);
              if(c*2>split1 && (totalCount-c)*2>split2){
                   return i;
              }
         }
          
          return -1;
    }
}