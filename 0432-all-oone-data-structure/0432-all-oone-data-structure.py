class AllOne {
public:
unordered_map<string,int>mp;
    AllOne() {
        
    }
    
    void inc(string key) {
        mp[key]++;
    }
    
    void dec(string key) {
         mp[key]--;
         if(mp[key]==0)
         mp.erase(key);
    }
    
    string getMaxKey() {
        int maxV=0;
        string str="";
        for(auto& it:mp)
        {
            if(it.second>maxV)
            {str=it.first;
             maxV=it.second;}
        }
        return str;

    }
    
    string getMinKey() {
        int minV=INT_MAX;
        string str="";
        for(auto& it:mp)
        {
            if(it.second<minV)
            {
                 str=it.first;
                 minV=it.second;
            }
            
        }
        return str;
    }
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne* obj = new AllOne();
 * obj->inc(key);
 * obj->dec(key);
 * string param_3 = obj->getMaxKey();
 * string param_4 = obj->getMinKey();
'' */