class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int[] counts = new int[2001];
        for(int n : arr1){
            counts[n]++;
        }

        for(int n : arr2){
            if(counts[n] >= 1){
                counts[n]++;
            }
        }

        // for(int n : arr2){
        //     if(counts[n] >=2){
        //         counts[n]++;
        //     }
        // }
        List<Integer> result = new ArrayList<>();

        for(int n : arr3){
            if(counts[n] >=2){
                result.add(n);
            }
        }

        return result;

    }
}