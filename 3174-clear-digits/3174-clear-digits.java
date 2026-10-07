class Solution {
    public String clearDigits(String s)
    {
        int len = s.length();
        StringBuilder str = new StringBuilder ();
        int i=-1;

        for(int j=0;j<len;j++)
        {
            char c = s.charAt(j);
            if(c>='a' && c<='z')
            {
                i++;
                str.append(c);
            }
                
            else{
                if(i>=0)
                {
                    str.deleteCharAt(i);
                    i--;
                }
            }
        }

        return str.toString();
    }
}