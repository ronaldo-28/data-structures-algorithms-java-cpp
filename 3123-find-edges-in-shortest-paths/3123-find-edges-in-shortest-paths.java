class Solution {
    class Node{
        int pos, wt, idx;

        Node(int pos, int wt, int idx){
            this.pos = pos;
            this.wt = wt;
            this.idx = idx;
        }

        Node(int pos, int wt){
            this(pos, wt, -1);
        }
    }
    public boolean[] findAnswer(int n, int[][] edges) {
        List<Node>[] graph = new LinkedList[n];
        for(int i=0;i<n;i++) graph[i] = new LinkedList<>();
        for(int i=0;i<edges.length;i++){
            int a = edges[i][0], b = edges[i][1], wt = edges[i][2];
            graph[a].add(new Node(b, wt, i));
            graph[b].add(new Node(a, wt, i));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.wt, b.wt));
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;
        pq.add(new Node(0, 0));
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(node.wt>minDist[node.pos]) continue;
            for(Node next: graph[node.pos]){
                int nextPos = next.pos, nextWt = node.wt + next.wt;
                if(nextWt>=minDist[nextPos]) continue;
                minDist[nextPos] = nextWt;
                pq.add(new Node(nextPos, nextWt));
            }
        }

        int minDistToTarget = minDist[n-1];
        boolean[] res = new boolean[edges.length];
        boolean[] visited = new boolean[n];
        visited[n-1] = true;
        pq.add(new Node(n-1, 0));
        while(!pq.isEmpty()){
            Node node = pq.poll();
            for(Node next: graph[node.pos]){
                int nextPos = next.pos, nextWt = node.wt + next.wt;  
                if(minDist[nextPos]+nextWt==minDistToTarget){
                    res[next.idx] = true;
                    if(visited[nextPos]) continue;
                    visited[nextPos] = true;
                    pq.add(new Node(nextPos, nextWt));
                }    
            }
        }

        return res;
    }
}