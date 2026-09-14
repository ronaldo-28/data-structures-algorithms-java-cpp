/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    int numComponents(ListNode* head, vector<int>& nums) {
        int hash[10001] = {0};
        
        for(int i=0;i<nums.size();i++){
            hash[nums[i]]++;
        }
        ListNode* mover = head;
        int ans = 0, curr = 0;
        while (mover) {
            if (hash[mover->val] > 0) {
                curr++;
            } else if(curr>0){
                curr = 0;
                ans++;
            }else{
                curr=0;
            }
            mover=mover->next;
        }
        if(curr>0){
            ans++;
        }
        return ans;
    }
};