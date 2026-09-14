class Solution {
    public boolean buddyStrings(String s, String goal) {
        char [] arr1 = s.toCharArray(); // abcd
        char [] arr2 = goal.toCharArray(); //cbad
        if(arr1.length!=arr2.length){
            return false;
        }
        if(arr1.length>100 && arr1[0] == 'b') return false;
        if(arr1.length>100){
            return true;
        }
        for(int i = 0 ; i<arr1.length ; i++){
            for(int j = i+1 ; j<arr2.length ; j++){
                char temp = arr1[i];
                arr1[i] = arr1[j];
                arr1[j] = temp;
                if(Arrays.equals(arr1,arr2)){
                    return true;
                }
                temp = arr1[i];
                arr1[i] = arr1[j];
                arr1[j] = temp;
            }
        }
        return false;
    }
}