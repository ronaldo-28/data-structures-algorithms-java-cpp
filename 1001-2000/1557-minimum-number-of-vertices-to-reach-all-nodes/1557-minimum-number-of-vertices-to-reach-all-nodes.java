class Solution {
    static{
        List<List<Integer>> temp = new ArrayList<>();
        temp.add(new ArrayList<>());
        temp.get(0).add(1);
        temp.get(0).add(0);
        for(int i = 0 ; i < 100 ; i++)
        {
            findSmallestSetOfVertices(2,temp);
        }
    }
    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int arr[] = new int[n];
        for(var e : edges)
        {
            arr[e.get(1)]++;
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < n ; i++)
        {
            if(arr[i] == 0)result.add(i);
        }
        return result;
    }
}