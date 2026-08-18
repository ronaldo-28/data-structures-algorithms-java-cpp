#include <deque>
using namespace std;

class HitCounter {
public:
    deque<int> q; 
    
    HitCounter() {}
    
    void hit(int timestamp) {
        q.push_back(timestamp);
    }
    
    int getHits(int timestamp) {
        while (!q.empty() && q.front() <= timestamp - 300) {
            q.pop_front();
        }
        return q.size();
    }
};

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter* obj = new HitCounter();
 * obj->hit(timestamp);
 * int param_2 = obj->getHits(timestamp);
 */