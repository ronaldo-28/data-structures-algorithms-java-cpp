class Solution {
public:
    int findLeastNumOfUniqueInts(vector<int>& arr, int k) {
        int n=arr.size();
        sort(arr.begin(),arr.end());
        vector<int> v;
        int i,j;
        i=0;
        j=0;
        int cnt=1;
        for(int i=1;i<n;i++){
            if(arr[i]==arr[i-1]){
                cnt++;
            }
            else{
                v.push_back(cnt);
                cnt=1;
            }
        }
        v.push_back(cnt);
        




        sort(v.begin(),v.end());
        n=v.size();
        for(int ele:v){
            if(k>=ele){
                k-=ele;
                n--;
            }
            else{
                break;
            }
            
            
            


        }
        return n;




        




        
    }
};