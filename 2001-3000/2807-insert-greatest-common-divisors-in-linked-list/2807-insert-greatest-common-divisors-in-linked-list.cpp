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
    ListNode* insertGreatestCommonDivisors(ListNode* head) {
        if(!head || !head->next) return head;
        ListNode* temp = head;
        while(temp->next){
            ListNode* nxt = temp->next;
            ListNode* node = new ListNode(gcd(temp->val,nxt->val));
            temp->next = node;
            node->next = nxt;
            temp = nxt;
        }
        return head;
    }
};