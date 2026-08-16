class Solution {
public:
int g1(vector<int>& nums1, vector<int>& nums2){
    int n=nums1.size();
    int curr=0;
    int maximum=0;
    for(int i=0;i<n;i++){
        curr=max(0,curr+(nums2[i]-nums1[i]));
        maximum=max(maximum,curr);
    }
    return maximum;

}

    int maximumsSplicedArray(vector<int>& nums1, vector<int>& nums2) {
        int sum1=accumulate(nums1.begin(),nums1.end(),0);
        int sum2=accumulate(nums2.begin(),nums2.end(),0);
        int x=g1(nums1,nums2);
        int y=g1(nums2,nums1);
        int res=max(sum1+x,sum2+y);
        return res;

    }
};