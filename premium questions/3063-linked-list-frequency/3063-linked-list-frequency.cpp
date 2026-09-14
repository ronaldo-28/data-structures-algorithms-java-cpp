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
    ListNode* frequenciesOfElements(ListNode* head) {
        unordered_map<int, int> freq;
        ListNode *ptr = head;
        while(ptr) {
            freq[ptr->val]++;
            ptr = ptr->next;
        }
        ListNode *parent = nullptr;
        ptr = head;
        for(const pair<int, int>& p: freq) {
            ptr->val = p.second;
            parent = ptr;
            ptr = ptr->next;
        }
        parent->next = nullptr;
        return head;
    }
};