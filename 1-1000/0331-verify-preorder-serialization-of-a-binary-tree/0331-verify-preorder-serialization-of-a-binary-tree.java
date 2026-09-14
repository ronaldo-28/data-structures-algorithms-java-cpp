class Solution {
    int index = 0;
    boolean ans = true;

    public boolean isValidSerialization(String preorder) {
        
        isValid(preorder.toCharArray());
         
        return ans && index == preorder.length()+1;
    }

    private void isValid(char[] array) {
        int index1= index;
        if (index >= array.length) {
            ans = false;
            return;
        } else if (array[index] == '#') {
            index = index+2;
            return;
        } else {
            while (index < array.length && array[index] != ',' ) {
                index++;
            }
            index++;
            isValid(array);
            isValid(array);
        }

    }
}