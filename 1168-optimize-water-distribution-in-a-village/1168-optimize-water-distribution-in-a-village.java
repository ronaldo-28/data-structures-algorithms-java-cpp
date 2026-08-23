class Pipe {
    int source;
    int destination;
    int minCost;
    public Pipe(int source, int destination, int minCost){
        this.source = source;
        this.destination = destination;
        this.minCost = minCost;
    }
}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    int find(int index){
        if(index != parent[index]){
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }

    boolean union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px != py){
            if(rank[px] < rank[py]){
                parent[px] = py;
            }else if(rank[py] < rank[px]){
                parent[py] = px;
            }else{
                rank[px]++;
                parent[py] = px;
            }
            return true;
        }
        return false;
    }
}

class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        PriorityQueue<Pipe> queue = new PriorityQueue<>(pipes.length+wells.length, 
            new Comparator<Pipe>(){
                public int compare(Pipe a, Pipe b){
                    return a.minCost - b.minCost;
                }
            });

        for(int i=0;i<wells.length;i++){
            Pipe pipe = new Pipe(0, i+1, wells[i]);
            queue.add(pipe);
        }

        for(int i=0;i<pipes.length;i++){
            Pipe pipe = new Pipe(pipes[i][0], pipes[i][1], pipes[i][2]);
            queue.add(pipe);
        }
        DSU dsu = new DSU(n+1);
        int cost = 0;
        int edges = 0;
        while(!queue.isEmpty()){
            Pipe p = queue.poll();
            
            if(dsu.union(p.source, p.destination)){
                cost += p.minCost;
                edges++;
                if(edges == n){
                    break;
                }
            }
        }
        return cost;
    }
}