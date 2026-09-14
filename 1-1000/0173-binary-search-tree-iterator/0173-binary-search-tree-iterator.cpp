class BSTIterator {
public:
    stack<int>st;
    stack<int>st1;
    void dfs(TreeNode* root, stack<int>&st){
        if(root== NULL){
            return;
        }
        dfs(root ->left, st);
        st.push(root ->val);
        dfs(root ->right, st); 
    }
    BSTIterator(TreeNode* root) {
        dfs(root, st1);
        while(!st1.empty()){
            st.push(st1.top());
            st1.pop();
        }
    }
    int next() {
        int top = st.top();
        st.pop();
        return top;
    }
    bool hasNext() {
        if(!st.empty()){
            return true;
        }
        return false;
    }
};