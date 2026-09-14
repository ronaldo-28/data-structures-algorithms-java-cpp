class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n=s.length();
        if(s.charAt(n-1)=='1')
        {
            return false;
        }
        if(n==2)
        {
            return true;
        }
        Deque<int[]> dq=new ArrayDeque<int[]>();
        dq.offerLast(new int[]{0, 0});
        while(dq.isEmpty()==false)
        {
            int arr[]=dq.pollFirst();
            int sind=arr[0], maxind=arr[1];
            if(maxind==n-1)
            {
                return true;
            }
            for(int i=sind;i<=maxind;i++)
            {
                if(s.charAt(i)=='0')
                {
                    int nextsind=i+minJump;
                    int nextendind=Math.min(i+maxJump, n-1);
                    if(nextsind>=n)
                    {
                        break;
                    }
                    if(nextsind<=maxind)
                    {
                        maxind=Math.max(maxind, nextendind);
                        if(maxind==n-1)
                        {
                            return true;
                        }
                    }
                    else
                    {
                        if(dq.isEmpty())
                        {
                            dq.offerLast(new int[]{nextsind, nextendind});
                        }
                        else if(nextsind<=dq.peekLast()[1])
                        {
                            dq.peekLast()[1]=nextendind;
                        }
                        else
                        {
                            dq.offerLast(new int[]{nextsind, nextendind});
                        }
                    }

                }
            }
        }
        return false;
    }
}