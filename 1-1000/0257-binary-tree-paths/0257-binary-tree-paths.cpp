class Solution {
private:
    vector<string> path;
    
    void btp(TreeNode* node, string s)
    {
        if(node == nullptr) return;
        if(s.length() > 0)
        {
            s.push_back('-');
            s.push_back('>');   
        }
        // Convert int to string
        string val = to_string(node->val);
        for(int i = 0; i < val.length(); ++i)
        {
            s.push_back( val[i] );   
        } 
        
        if(node->left == nullptr && node->right == nullptr)
            path.push_back(s);
        
        else
        {
            btp(node->left, s);
            btp(node->right, s);
        }
    }
public:
    vector<string> binaryTreePaths(TreeNode* root) {
        string s = "";
        btp(root, s);
        return path;
    }
};