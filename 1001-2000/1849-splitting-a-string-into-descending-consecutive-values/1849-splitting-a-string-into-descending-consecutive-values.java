class Solution {
    public boolean splitString(String s) {
        return backTrack( s , 0, -1, 0 );
    }
    
    public boolean backTrack( String s, int index, long prevNumber, int count )
    {
        if( index >= s.length() )
        {
            if( count >= 2) 
                return true;
            return false;
        }
        
        long currNo=0;
        for( int i = index; i < s.length() ;i++ )
        {
            currNo = currNo*10+(s.charAt(i)-'0');
            if( prevNumber == -1 || ( prevNumber - currNo ) == 1 )
                if( backTrack( s,i+1,currNo, count+1) )
                    return true;
        }
    return false;
    }
}