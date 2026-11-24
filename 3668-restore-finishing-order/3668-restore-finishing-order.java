class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {

        //friends -- 1 to 8 only 
       
       int count[]=new int[101];
        int arr[]=new int[friends.length];
        int x=0;

            for(int i=0;i<order.length;i++){
                count[order[i]]++;
            }

            for(int j=0;j<friends.length;j++){
                count[friends[j]]++;
            }

           for(int k=0;k<order.length;k++){
                if(count[order[k]]==2)
                    arr[x++]=order[k];
                    
           }
        
            return arr;
    }

}