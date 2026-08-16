class Solution {
    public int minimumPartition(String s, int k) {
        char ch[]=s.toCharArray();
        int l=ch.length;
        long sum=0;
        int c=0;
        if(k<10)
        {
            for(int i=0;i<l;i++)
            {if(ch[i]-48>k)
            {return -1;}}
            return l;
        }
        for(int i=0;i<l;i++)
        {
            sum=sum*10+ch[i]-48;
    
            if(sum>k)
            {
                sum=ch[i]-48;
                c++;
            }
        }
        return c+1;
    }
}