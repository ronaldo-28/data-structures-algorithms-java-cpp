class Solution {
public:
    ListNode* removeElements(ListNode* head, int val) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* current = dummy;
        
        while (current->next) {
            if (current->next->val == val) {
                current->next = current->next->next;  // Remove node
            } else {
                current = current->next;  // Move to next node
            }
        }
        
        return dummy->next;
    }
};