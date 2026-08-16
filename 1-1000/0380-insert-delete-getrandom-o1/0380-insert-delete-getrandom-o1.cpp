// class RandomizedSet {
// public:
//     vector<int> nums;
//     unordered_map<int,int> mp;

//     RandomizedSet() {}

//     bool insert(int val) {
//         if(mp.count(val)) return false;
//         nums.push_back(val);
//         mp[val] = nums.size() - 1;
//         return true;
//     }

//     bool remove(int val) {
//         if(!mp.count(val)) return false;

//         int idx = mp[val];            // index of val
//         int last = nums.back();       // last element

//         nums[idx] = last;             // swap with last
//         mp[last] = idx;               // update index of last

//         nums.pop_back();              // remove last
//         mp.erase(val);                // remove val from map

//         return true;
//     }

//     int getRandom() {
//         int idx = rand() % nums.size();
//         return nums[idx];
//     }
// };


class RandomizedSet {
public:

vector<int>list;
unordered_map<int,int>map;

    RandomizedSet() {
         
    }
    
    bool insert(int val) {
        if(map.count(val)){
            return false;
        }
            list.push_back(val);
          map[val]=list.size()-1;
            return true;
    
    }
    
    bool remove(int val) {
        if(!map.count(val)) {
            return false;
        }

        int idx=map[val];
        int last=list.back();

        //swap with last
        list[idx]=last;
        map[last]=idx;

        
        list.pop_back();
        map.erase(val);
        return true;
    }
    
    int getRandom() {
      return list[rand()% list.size()];
    }
};
auto init = atexit([]{ofstream("display_runtime.txt")<<"0";});
/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */