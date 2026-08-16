class MKAverage {
public:
    MKAverage(int m, int k)
        : m_{static_cast<size_t>(m)}, k_{static_cast<size_t>(k)} {}

    void addElement(int num) {
        q_[back_++] = num;
        ++cnt_[num];
        sum_ += num;
        if (back_ - front_ > m_) {
            num = q_[front_++];
            sum_ -= num;
            if (auto it{cnt_.find(num)}; --it->second == 0)
                cnt_.erase(it);
        }
    }

    int calculateMKAverage() {
        if (back_ - front_ < m_)
            return -1;

        int64_t s{0};
        auto i{k_};
        for (const auto [num, c] : cnt_) {
            s += num * (i > c ? (i -= c, c) : exchange(i, 0));
            if (i == 0)
                break;
        }
        i = k_;
        for (const auto [num, c] : cnt_ | views::reverse) {
            s += num * (i > c ? (i -= c, c) : exchange(i, 0));
            if (i == 0)
                break;

        }
        return (sum_ - s) / (m_ - (k_ << 1));
    }

private:
    array<int, 100000> q_;
    map<int, int> cnt_{};
    size_t front_{0};
    size_t back_{0};
    size_t m_;
    size_t k_;
    int64_t sum_{0};
};

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage* obj = new MKAverage(m, k);
 * obj->addElement(num);
 * int param_2 = obj->calculateMKAverage();
 */