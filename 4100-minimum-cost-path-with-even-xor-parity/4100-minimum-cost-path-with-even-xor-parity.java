class Solution {
    List<int[]>[] adj;
    long min_cost=Long.MAX_VALUE;
    public int minActivationCost(int[][] edges, int[] values) {
        int n = values.length;
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            //node1-{node2,weight}
            adj[edge[0]].add(new int[] { edge[1], edge[2] });
            adj[edge[1]].add(new int[] { edge[0], edge[2] });
        }

        int[] parity = new int[n];

        for (int i = 0; i < n; i++) {
            parity[i] = Integer.bitCount(values[i]) % 2;
        }
        dfs(0, -1, parity[0], parity);
        return (min_cost==Long.MAX_VALUE)?-1:(int)min_cost;
    }

    long[] dfs(int node, int parent, int curr_par, int[] parity) {
        long[] curr_best = new long[] { Long.MAX_VALUE, Long.MAX_VALUE };
        curr_best[curr_par] = 0;

        for (int[] edge : adj[node]) {
            int n=edge[0];
            int w=edge[1];

            if(n==parent)
            {
                continue;
            }

            int next_par=curr_par^parity[n];
            long[]sub_best=dfs(n, node, next_par, parity);

            for(int p1=0;p1<2;p1++)
            {
                for(int p2=0;p2<2;p2++)
                {
                    if(curr_best[p1]!=Long.MAX_VALUE && sub_best[p2]!=Long.MAX_VALUE)
                    {
                        if((p1^p2^parity[node])==0)
                        {
                            min_cost=Math.min(min_cost,curr_best[p1]+sub_best[p2]+w);
                        }
                    }
                }
            }

            for(int p=0;p<2;p++)
            {
                if(sub_best[p]!=Long.MAX_VALUE)
                {
                    curr_best[p]=Math.min(curr_best[p],sub_best[p]+w);
                }
            }
        }
        return curr_best;
    }
}