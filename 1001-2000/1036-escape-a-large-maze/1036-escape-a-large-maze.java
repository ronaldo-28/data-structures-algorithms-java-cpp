class Solution {
    
    int[]source;
    int[]target;
    int xmin;
    int xmax;
    int ymin;
    int ymax;
    HashSet<Node>blocks;
    HashSet<Node>been;
    LinkedList<Integer>vertical;
    LinkedList<Integer>horizontal;
    int[][]neigh={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    int[][]ds={{-1,0},{1,0},{0,-1},{0,1}};
    int[][]want;
    
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        this.source=target;
        this.target=target;
        blocks=new HashSet();
        been=new HashSet();
        vertical=new LinkedList();
        horizontal=new LinkedList();
        
        int xmirror=source[0]<=target[0]?1:-1;
        int ymirror=source[1]<=target[1]?1:-1;
        
        source[0]*=xmirror;
        source[1]*=ymirror;
        target[0]*=xmirror;
        target[1]*=ymirror;
        
        xmin=xmirror==1?0:(-1000000+1);
        xmax=xmirror==1?(1000000-1):0;
        ymin=ymirror==1?0:(-1000000+1);
        ymax=ymirror==1?(1000000-1):0;
        
        for(int[]b:blocked){
            int x=(b[0]*=xmirror),y=(b[1]*=ymirror);
            blocks.add(new Node(x,y));
            if(y==source[1])horizontal.offer(x);
            if(x==target[0])vertical.offer(y);
        }
        Collections.sort(horizontal);
        Collections.sort(vertical);
        
        want=new int[][]{{source[0]+1,target[0]-1,source[1],source[1]},{target[0],target[0],source[1],target[1]}};
        
        return dfs(source[0],source[1],true);   
    }
    private boolean dfs(int x,int y,boolean jump){
        if(x==target[0]&&y==target[1])return true;
        if(jump){
            if(x!=target[0]){
                ceil(horizontal,x);
                if(horizontal.isEmpty()||horizontal.peek()>target[0]){
                    want[0][0]=want[0][1]+1;//means disabled
                    want[1][2]=y+1;
                    return dfs(target[0],y,true);
                }
                else{
                    int nx=horizontal.peek()-1;
                    been.add(new Node(nx,y));
                    want[0][0]=nx+1;
                    return dfs(nx,y,false);
                }
            }
            else{
                ceil(vertical,y);
                if(vertical.isEmpty()||vertical.peek()>target[1])
                    return true;
                else{
                    int ny=vertical.peek()-1;
                    been.add(new Node(x,ny));
                    want[1][2]=ny+1;
                    return dfs(x,ny,false);
                }
            }
        }
        for(int[]d:ds){
            int nx=x+d[0], ny=y+d[1];
            Node node=new Node(nx,ny);
            if(nx>=xmin&&nx<=xmax&&ny>=ymin&&ny<=ymax
               &&!blocks.contains(node)&&!been.contains(node)
               &&hasBlockedNeighbor(nx,ny)){
                been.add(node);
                if(readyToJump(nx,ny)&&dfs(nx,ny,true))return true;
                if(dfs(nx,ny,false))return true;
            }
        }
        return false;
    }
    private void ceil(LinkedList<Integer>list,int n){
        while(!list.isEmpty()&&list.peek()<n)list.poll();
    }
    private boolean hasBlockedNeighbor(int x,int y){
        for(int[]ngh:neigh){
            if(blocks.contains(new Node(x+ngh[0],y+ngh[1])))
                return true;
        }
        return false;
    }
    private boolean readyToJump(int x,int y){
        if(x>=want[0][0]&&x<=want[0][1]&&y>=want[0][2]&&y<=want[0][3]){
            want[0][0]=x+1;
            return true;
        }
        if(x>=want[1][0]&&x<=want[1][1]&&y>=want[1][2]&&y<=want[1][3]){
            want[1][2]=y+1;
            return true;
        }
        return false;
    }   
}
class Node {
    int x;
    int y;
    public Node(int xx,int yy){
        x=xx;
        y=yy;
    }
    
    public int hashCode(){
        return x+20773*y;
    }
    public boolean equals(Object obj){
        if(obj instanceof Node){
            Node n=(Node)obj;
            return x==n.x&&y==n.y;
        }
        return false;
    }
}