class Solution {
    
    int aliceCount;
    int bobCount;
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        aliceCount=n;
        bobCount=n;
        int alice_rank[]=new int[n+1];
        int alice_parent[]=new int[n+1];
        int bob_rank[]=new int[n+1];
        int bob_parent[]=new int[n+1];
        for(int i=0;i<=n;i++){
            alice_parent[i]=i;
            bob_parent[i]=i;
        }
        int countEdges=0;
        for(int i = 0; i < edges.length ; i++){
            int [] e = edges[i];
            if(e[0] == 3) {
                boolean bool1=union(e[1], e[2],alice_parent,alice_rank,1);
                boolean bool2=union(e[1], e[2],bob_parent,bob_rank,2);
                if(bool1==true || bool2==true)
                    countEdges++;
                if(aliceCount==1 && bobCount==1)
                    return edges.length-countEdges; 
            }   
        }
        if(aliceCount!=1){
            for(int i = 0; i < edges.length ; i++){
                int [] e = edges[i];
                if(e[0] == 1) {
                    boolean bool1=union(e[1], e[2],alice_parent,alice_rank,1);
                    if(bool1==true)
                        countEdges++;
                    if(aliceCount==1){
                        if(bobCount==1)
                            return edges.length-countEdges; 
                        else
                            break;
                    }
                }
            }
        }
        if(aliceCount!=1)
            return -1;
        if(bobCount!=1){
            for(int i = 0; i < edges.length ; i++){
                int [] e = edges[i];
                if(e[0] == 2) {
                    boolean bool1=union(e[1], e[2],bob_parent,bob_rank,2);
                    if(bool1==true)
                        countEdges++;
                    if(bobCount==1){
                        if(aliceCount==1)
                            return edges.length-countEdges; 
                        else
                            break;
                    }
                }
            }
        }
        return -1;
    }
    public int find(int x,int parent[]){
        if(x==parent[x])
            return x;
        return parent[x]=find(parent[x],parent);
    }
    public boolean union(int x,int y,int parent[],int rank[],int type){
        int parent_x=find(x,parent);
        int parent_y=find(y,parent);
        if(parent_x==parent_y){
            return false;
        }
        if(rank[parent_x]>=rank[parent_y]){
            parent[parent_y]=parent_x;
            rank[parent_x]++;
        }
        else{
            parent[parent_x]=parent_y;
            rank[parent_y]++;
        }
        if(type==1)
            aliceCount--;
        else if(type==2)
            bobCount--;
        return true;
    }
    public boolean oneComponent(int parent[],int rank[]){
        int comp=find(1,parent);
        for(int i=2;i<rank.length;i++){
            if(comp!=find(i,parent))
                return false;
        }
        return true;
    }    
}