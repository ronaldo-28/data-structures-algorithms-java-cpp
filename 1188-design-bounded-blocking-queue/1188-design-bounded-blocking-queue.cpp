class BoundedBlockingQueue {
    /* mpmc queue --> producers add elements to tail, consumers consume from head
    we need mutexes for synchronization. */

    long head;
    long tail;
    size_t sz;
    size_t cap; 
    std::mutex m;
    std::vector<int> data; // this should ideally be a generic type, T, but the interface is fixed here. 
    /* simplest version just locks the entire queue for each operation
    this is probably ok for most workloads, and we can batch consume/produce in the worst case.
    we can try to fine tune the impl without locks, if the need arises. But we start with the simplest 
    solution first. */
    std::condition_variable full, empty;
    
public:
    BoundedBlockingQueue(int capacity) : head(0), tail(0), sz(0), cap(capacity), m(), data(cap, 0), full(), empty() {
        
    }
    
    void enqueue(int element) {
        std::unique_lock<mutex> lk(m);
        if (tail - head == cap) {
            full.wait(lk, [&]() { return (tail - head) < cap; } );
        }
        auto idx = tail % cap;
        data[idx] = element; // assume copyable
        tail++; // tail represents slot past the last element in the queue
        empty.notify_all();
    }
    
    int dequeue() {
        std::unique_lock<mutex> lk(m);
        if (tail - head == 0) {
            empty.wait(lk, [&](){ return tail - head != 0; });
        }

        auto idx = head % cap;
        auto ele = data[idx];
        head++;
        full.notify_all();
        assert(tail - head >= 0);
        return ele;
    }
    
    int size() {
        std::unique_lock<mutex> lk(m);
        return tail - head;      
    }
};