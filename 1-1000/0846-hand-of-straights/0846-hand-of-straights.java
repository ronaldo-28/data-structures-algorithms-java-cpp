
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if(n % groupSize != 0) return false;

        quickSort(hand, 0 , n - 1);
        
        int i  = 0;
        for(; i < n; i++){
            if(hand[i] >= 0){
                if(!findSucessors(hand,groupSize, i, n)){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean findSucessors(int[] hand, int groupSize, int i, int n){
        
        int next = hand[i] + 1;
        hand[i] = -1;
        int count = 1;
        i += 1;
        while(i< n && count < groupSize){
            if(next == hand[i]){
                next = hand[i] + 1;
                hand[i] =-1;
                count++;
            }
            i++;
        }

        if(count != groupSize) return false;

        return true;
    }


    public void quickSort(int[] hand, int low, int high){
        if(low < high){

            int pi = partition(hand, low, high);

            quickSort(hand, low, pi - 1);
            quickSort(hand, pi + 1, high);
        }
    }

    public int partition(int[] hand, int low, int high){
        int pivot = hand[high];

        int i = low -1;

        for(int j = low; j < high; j++){
            if(hand[j] < pivot){
                i++;
                int temp = hand[i];
                hand[i] = hand[j];
                hand[j] = temp; 
            }
        }
        int temp = hand[i + 1];
        hand[i + 1] = hand[high];
        hand[high] = temp;     
        return i + 1;
    }

}