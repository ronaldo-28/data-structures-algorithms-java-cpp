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
private:
    std::array<int, 2> dfs(TreeNode* root) {
        // Returns: `{ cnt_when_result_true, cnt_when_result_false }`
        if (root == nullptr) {
            return { 0, 0 };
        } else if (root->val == 0) {
            return { 1, 0 };
        } else if (root->val == 1) {
            return { 0, 1 };
        }

        std::array<int, 2> leftRes = dfs(root->left);
        std::array<int, 2> rightRes = dfs(root->right);
        if (root->val == 2) {
            return { std::min(leftRes[0], rightRes[0]), leftRes[1] + rightRes[1] };
        } else if (root->val == 3) {
            return { leftRes[0] + rightRes[0], std::min(leftRes[1], rightRes[1]) };
        } else if (root->val == 4) {
            return {
                std::min(leftRes[0] + rightRes[1], leftRes[1] + rightRes[0]),
                std::min(leftRes[0] + rightRes[0], leftRes[1] + rightRes[1])
            };
        } else if (root->val == 5) {
            return {
                (root->left != nullptr) ? leftRes[1] : rightRes[1],
                (root->left != nullptr) ? leftRes[0] : rightRes[0]
            };
        }
        return { 0, 0 };
    }

public:
    int minimumFlips(TreeNode* root, bool result) {
        // If:
        //  root->val == 0: return result ? 1 : 0;
        //  root->val == 1: return result ? 0 : 1;
        //  root->val == 2: return result ? std::min(minimumFlips(root->left, true), minimumFlips(root->right, true) :
        //                                  minimumFlips(root->left, false) + minimumFlips(root->right, false);
        //  root->val == 3: return result ? minimumFlips(root->left, true) + minimumFlips(root->right, true) :
        //                                  std::min(minimumFlips(root->left, false), minimumFlips(root->right, false));
        //  root->val == 4: return result ? std::min(minimumFlips(root->left, true) + minimumFlips(root->right, false),
        //                                           minimumFlips(root->left, false) + minimumFlips(root->right, true)) :
        //                                  std::min(minimumFlips(root->left, true) + minimumFlips(root->right, true),
        //                                           minimumFlips(root->left, false) + minimumFlips(root->right, false));
        //  root->val == 5: return result ? ((root->left != nullptr) ? minimumFlips(root->left, false) : minimumFlips(root->right, false)) :
        //                                  ((root->left != nullptr) ? minimumFlips(root->left, true) : minimumFlips(root->right, true));

        // Traverse the tree in post-order, build up the cnt for each tree node in bottom-up manner
        std::array<int, 2> res = dfs(root);
        return result ? res[0] : res[1];
    }
};