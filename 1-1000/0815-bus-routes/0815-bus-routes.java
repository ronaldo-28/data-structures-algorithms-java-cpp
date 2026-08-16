class Solution {

    public int numBusesToDestination(int[][] routes, int source, int target) {

        if(source==target) return 0;

        int minStop = -1;
        int n= routes.length;

        for(int[] route: routes){
            for(int a: route){
                minStop = Math.max( minStop, a);
            }
        }

        if(target>minStop || source>minStop) return -1;

        int[] arr = new int[minStop+1];
        Arrays.fill(arr, n+1);
        arr[source]=0;
        
        boolean flag=true;
        while(flag){

            flag=false;
            for(int[] route: routes){
                int min=n+1;
                for(int stop:route){
                    min = Math.min(min, arr[stop]);
                }
                min++;

                for(int stop:route){
                    if(arr[stop]>min) {
                        arr[stop]=min;
                        flag=true;
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(arr));
        return arr[target]<n+1?arr[target]:-1;    
    }
}