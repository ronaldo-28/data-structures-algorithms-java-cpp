class Solution 
{
    static void dfs( int curr, List<List<Integer>>rooms, int vis[])
    {
         vis[curr]= 1;
         
         for( int conn  : rooms.get(curr) )
         {
              if( vis[conn]==0)
              {
                   dfs( conn , rooms, vis);
              }
         }
    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms)
    {
        int n = rooms.size();
        
        int vis[]= new int[n];
        
        dfs(0, rooms, vis);
        
        for( int i=0;i<n;i++)
        {
             if( vis[i]==0)
                 return false;
        }
        
        return true;
    }
}