class Solution {
public:
    int maximizeSweetness(vector<int>& sweetness, int k) {
        int left = 1;
        int right = 0;
        for(int sweet : sweetness){
            right +=sweet;
        }
        right /=(k+1);
        int optimal_sweetness = 0;
        //binary search 
        while(left <= right){
            int mid = left + (right - left)/2;
            if(thisWorks(mid, sweetness, k)){
                optimal_sweetness = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return optimal_sweetness;
    }

    bool thisWorks(int mid, vector<int>& sweetness, int k){
        //check whether we can create k + 1 peices with atleast mid sweetness each
        int currSweetness = 0;
        int totalCandidates = 0;
        for(int s : sweetness){
            currSweetness += s;
            if(currSweetness >= mid){
                currSweetness = 0;
                totalCandidates++;
            }
        }
        if(totalCandidates >= k+ 1){
            return true;
        }
        return false;
    }
};