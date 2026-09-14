class MRUQueue {
public:
    vector<int> mru; 

    MRUQueue(int n) {
        for (int i = 1; i <= n; i++) {
            mru.push_back(i); 
        }
    }
    
    int fetch(int k) {
        int f = mru[k-1];
        mru.erase(mru.begin() + k-1); 
        mru.push_back(f);
        return f; 
    }
};

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue* obj = new MRUQueue(n);
 * int param_1 = obj->fetch(k);
 */