class Solution {
    public boolean isPossible(int[] target) {
       long sum = 0;
       int max = 0;
       for(int i = 0 ; i<target.length ; i++){
        sum+=target[i];             // calculate sum of target array
        if(target[i]>target[max]){   // find max in target array 
            max = i;                 // if found make max = i means max will be maximum element from target array its index get store in max
        }
       }
       long diff = sum - target[max];  // calculate diff by subtracting max value from sum value.
       if(target[max]==1 || diff == 1){//1st case is when we find our answer so any index of target = 1 we return true 2nd case if we get array like {1,9} here sum = 10 , diff = 10-9 = 1 so  diff = 1 then 9 becomes 9-1 = 8 then 8-1 = 7 and so on so eventuall we know it will get 1 so thats why if diff == 1 we will return 1 asap
        return true;
       }
       if(diff>target[max] || diff == 0 || target[max]%diff == 0){   //1st case diff>target[max] means negative case when we later do target[max]%=diff this will give negative value and 2nd case is when we have only 1 character in array eg {2} then if we need to calculate diff  we cant because we will get same array again and again in recursion the 3rd case is for if we get array like {0,5} so it eventuall make their diff = 0 which return fasle. 
        return false;
       }
       target[max]%=diff;        // we can do subtract like target[max]=target[max]-diff but it will take 1 1 step and becomes slow thats why we jump unnecessary step so do target[max]%=diff
       return isPossible(target); // recursive call for target array again
    }
}