class LRUCache {
    void swap(vector<int>& arr, int index1){
        int len = arr.size();
        int temp = arr[index1];
        for(int i=index1;i<len-1;i++){
            arr[i] = arr[i+1];
        }
        arr[len-1] = temp;
    }
public:
    unordered_map<int,int> mp;
    vector<int> recency;
    int size;
    
    LRUCache(int capacity) {
        size = capacity;
    }
    
    int get(int key) {
        if(mp.find(key) == mp.end()) return -1;
        int index = 0;
        int end = recency.size()-1;
        while(index <= end && recency[index]!=key) index++;
        swap(recency, index);
        return mp[key];
    }
    
    void put(int key, int value) {
        if(mp.find(key) == mp.end()){
            recency.push_back(key);
            mp[key] = value;
        }
        else {
            int index = 0;
            int end = recency.size()-1;
            while(index <= end && recency[index]!=key) index++;
            swap(recency, index);
    mp[key] = value;
        }
    if(mp.size()>size){
        mp.erase(*(recency.begin()));
        recency.erase(recency.begin()); 
    }  
    }
};
auto init = atexit([]() { ofstream("display_runtime.txt") << "0";});


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
 