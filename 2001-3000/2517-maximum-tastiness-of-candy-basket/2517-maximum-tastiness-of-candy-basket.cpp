class Solution {
public:
bool check(vector<int>& arr, int k,int m){
    int n = arr.size();
    int curr=arr[0];
    k--;
    int r=1;
    while(r<n){
        if(arr[r]-curr>=m){
            k--;
            curr=arr[r];
        }
        r++;
    }
    if(k<=0) return true;
    return false;
}

    int maximumTastiness(vector<int>& arr, int k) {
        int n = arr.size();
        sort(arr.begin(),arr.end());
        int l=0;
        int h=arr[n-1]-arr[0];
        while(l<=h){
            int m =(l+h)/2;
            if(check(arr,k,m)){
                l=m+1;
            }
            else h= m-1;
        }
        return h;
    }
};