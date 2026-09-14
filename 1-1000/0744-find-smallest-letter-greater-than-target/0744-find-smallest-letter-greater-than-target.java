class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int low=0, high=letters.length -1;
        int mid;
        int pos=-1;
        while(low<=high)
        {
            mid = (low+high)/2;
            if(letters[mid]>target)
            {
                pos=mid;
                high = mid-1;
            }
            else
            {
                low=mid+1;
            }
        }
      return pos == -1 ? letters[0] : letters[pos];

    }
}