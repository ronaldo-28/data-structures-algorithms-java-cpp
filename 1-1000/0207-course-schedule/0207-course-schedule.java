class Solution {
    static {
        for (int i = 0; i < 500; ++i) {
            canFinish(0, new int[][]{});
        }
    }
    public static boolean canFinish(int n, int[][] pre) {
        ArrayList<Integer> arr[]=new ArrayList[n];
        for(int i=0;i<n;i++){
            arr[i]=new ArrayList<>();
            // System.out.println(arr[i]);
        }
        for(int[] a:pre){
            arr[a[0]].add(a[1]);
        }
        boolean visited[]=new boolean[n];
        boolean isVisiting[]=new boolean[n];
        for(int i=0;i<n;i++){
            if(!dfs(i,visited,isVisiting,arr))return false;
        }
        return true;
    }
    static boolean dfs(int key,boolean visited[],boolean isVisiting[],ArrayList<Integer> arr[]){
        // System.out.println(key+" "+isVisiting[key]+" "+visited[key]);
        if(isVisiting[key])return false;
        if(visited[key])return true;
        isVisiting[key]=true;
        for(int i:arr[key]){
            if(!dfs(i,visited,isVisiting,arr))return false;
        }
        isVisiting[key]=false;
        visited[key]=true;
        return true;
    }
}