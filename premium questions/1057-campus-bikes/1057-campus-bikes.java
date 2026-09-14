class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        

        // look at constraints!! use the distance!!

        List<int[]>[] distance = new ArrayList[2000];
        for(int i=0;i<2000;i++)
        {
            distance[i] = new ArrayList();
        }

        for(int i=0;i<workers.length;i++)
        {
            for(int j=0;j<bikes.length;j++)
            {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + 
                Math.abs(workers[i][1] - bikes[j][1]);
                distance[dist].add(new int[]{i,j});
            }
        }

        boolean[] takenWorker = new boolean[workers.length];
        boolean[] takenBikes = new boolean[bikes.length];
        int[] res = new int[workers.length];

        for(int i=0;i<2000;i++)
        {
            for(int j = 0; j < distance[i].size();j++)
            {
                if(!takenWorker[distance[i].get(j)[0]] && !takenBikes[distance[i].get(j)[1]])
                {
                    takenWorker[distance[i].get(j)[0]] = true;
                    takenBikes[distance[i].get(j)[1]] = true;
                    res[distance[i].get(j)[0]] = distance[i].get(j)[1];

                }
            }
        }

        return res;
    }
}