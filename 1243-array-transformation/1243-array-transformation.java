class Solution {
    public List<Integer> transformArray(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int prev, temp;
        boolean changed = true;
        while(changed){
            changed = false;
            prev = arr[0];
            for(int i = 1; i < arr.length-1; i++){
                temp = arr[i];
                if(arr[i] > prev && arr[i] > arr[i+1]){
                    changed = true;
                    arr[i]--;
                }
                else if(arr[i] < prev && arr[i] < arr[i+1]){
                    changed = true;
                    arr[i]++;
                }
                prev = temp;
            }
        }
        for(int num: arr) res.add(num);
        return res;
    }
}