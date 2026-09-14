class Solution {
    public int[] separateDigits(int[] arr) {
        int totalDigits = 0;

        for(int n : arr){
            int temp = n;

            if(temp == 0){
                totalDigits++;
            }
            else{
                while(temp>0){
                    totalDigits++;
                    temp/=10;
                }
            } 
        }
        

        int ans[] = new int[totalDigits];
        int index = totalDigits - 1;

        for(int i = arr.length-1; i>=0; i--){
            int num = arr[i];

            if(num == 0){
                ans[index--] = 0;
            }
            else{
                while(num>0){
                    ans[index--] = num%10;
                    num /= 10;
                }
            }
        }

        return ans;

    }
}