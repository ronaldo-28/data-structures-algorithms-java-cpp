class Solution
{
    public int passwordStrength(String p)
    {
        boolean [] a = new boolean['z'+1];

        for(int i=0, x=p.length(); i<x; i++)
            a[p.charAt(i)] = true;

        int r = 0;

        for(char c='a'; c<='z'; c++)
            if(a[c])
                r++;

        for(char c='A'; c<='Z'; c++)
            if(a[c])
                r += 2;

        for(char c='0'; c<='9'; c++)
            if(a[c])
                r += 3;

        if(a['!'])
            r += 5;

        if(a['@'])
            r += 5;

        if(a['#'])
            r += 5;

        if(a['$'])
            r += 5;

        return r;
    }
}