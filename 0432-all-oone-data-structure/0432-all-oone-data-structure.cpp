class AllOne {
    unordered_map<string,pair<int,list<string>::iterator>> mp;
    list<string> l;
public:
    AllOne() {
    }
    
    void inc(string key) {
        if(mp.find(key)==mp.end()){
            l.push_front(key);
            mp[key].second=l.begin();
        }
        mp[key].first++;

        if(mp[key].first>=mp[l.back()].first){
            l.splice(l.end(),l,mp[key].second);
        }
        else{
            auto it=mp[key].second;
            it++;
            if(it!=l.end() && mp[key].first>mp[*it].first){
                l.splice(++it,l,mp[key].second);
            }
        } 
    }
    
    void dec(string key) {
        mp[key].first--;
        if(mp[key].first<=mp[l.front()].first)
            l.splice(l.begin(),l,mp[key].second);

        if(mp[key].first==0){
            mp.erase(key);
            l.pop_front();
        }
        else if(mp.size()>1){
            auto it=mp[key].second;
            if(it!=l.begin()){
                it--;
                if(mp[key].first<mp[*it].first){
                    l.splice(--it,l,mp[key].second);
                }
            }
        } 
    }
    
    string getMaxKey() {
        if(l.empty()) return "";
        return l.back();
    }
    
    string getMinKey() {
        if(l.empty()) return "";
        return l.front();
    }
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne* obj = new AllOne();
 * obj->inc(key);
 * obj->dec(key);
 * string param_3 = obj->getMaxKey();
 * string param_4 = obj->getMinKey();
 */