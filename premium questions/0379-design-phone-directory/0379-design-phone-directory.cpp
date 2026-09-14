#include <vector>

using namespace std;

class PhoneDirectory {
public:
    PhoneDirectory(size_t n) : max_(n), is_free_(n, 1) {
        free_.reserve(n);
        for (int i = 0; i < n; i++) {
            free_.push_back(i);
        }
    }

    int get() {
        if (free_.empty()) {
            return -1;
        }

        int next = free_.back();
        free_.pop_back();

        is_free_[next] = 0;

        return next;
    }

    bool check(int number) { return is_free_[static_cast<size_t>(number)]; }

    void release(int number) {
        if (is_free_[static_cast<size_t>(number)]) {
            return;
        }
        is_free_[static_cast<size_t>(number)] = 1;
        free_.push_back(number);
    }

private:
    size_t max_;
    vector<char> is_free_;
    vector<int> free_;
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */