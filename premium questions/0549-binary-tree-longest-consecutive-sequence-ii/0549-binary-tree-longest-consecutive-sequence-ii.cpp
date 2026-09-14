/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int maxLen;

    Solution() {
        this->maxLen = 0;
    }

    pair<int, int> longestConsecutiveInternal(TreeNode* ptr, int parentVal) {
        if (ptr == nullptr) {
            return {0, 0};
        }

        pair<int, int> left = longestConsecutiveInternal(ptr->left, ptr->val);
        pair<int, int> right = longestConsecutiveInternal(ptr->right, ptr->val);

        this->maxLen = max(
            this->maxLen,
            left.first + 1 + right.second
        );
        this->maxLen = max(
            this->maxLen,
            right.first + 1 + left.second
        );

        return {
            (parentVal == ptr->val + 1)? max(left.first, right.first) + 1 : 0,
            (parentVal == ptr->val - 1)? max(left.second, right.second) + 1 : 0
        };
    }

    int longestConsecutive(TreeNode* root) {
        longestConsecutiveInternal(root, INT_MAX);
        return this->maxLen;
    }
};