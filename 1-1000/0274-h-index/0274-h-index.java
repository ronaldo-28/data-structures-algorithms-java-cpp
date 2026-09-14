class Solution {
    public int hIndex(int[] citations) {
        int n= citations.length;
        int[] bucket= new int [n+1];

        for( int c : citations)
        {
            if(c> n) bucket[n]++;
            else bucket[c]++;
        }

        int total=0;

        for(int i=n; i>=0; i--)
        {
            total+=bucket[i];

            if(total>=i)
            {
                return i;
            }
        }
        return 0;

        
    }
}