// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


/// Streaming statistics over a FIFO window using an augmented treap.
class StatisticsTracker {
   public:
    StatisticsTracker() { nodes_.reserve(MAX_NODES + 1); }

    // NOLINTNEXTLINE(readability-identifier-naming)
    void addNumber(int number) {  // LeetCode requires instance method
        fifo_.push_back(number);
        total_sum_ += number;
        ++total_count_;
        root_ = treap_add(root_, number);
    }

    // NOLINTNEXTLINE(readability-identifier-naming)
    void removeFirstAddedNumber() {  // LeetCode requires instance method
        const int value = fifo_.front();
        fifo_.pop_front();
        total_sum_ -= value;
        --total_count_;
        root_ = treap_remove(root_, value);
    }

    // NOLINTNEXTLINE(readability-identifier-naming)
    [[nodiscard]] auto getMean() const
        -> int {  // LeetCode requires instance method
        return static_cast<int>(total_sum_ / total_count_);
    }

    // NOLINTNEXTLINE(readability-identifier-naming)
    auto getMedian() -> int {  // LeetCode requires instance method
        return treap_kth(root_, total_count_ / 2);
    }

    // NOLINTNEXTLINE(readability-identifier-naming)
    auto getMode() -> int {  // LeetCode requires instance method
        return find_min_with_freq(root_, node_at(root_).max_freq);
    }

   private:
    struct TreapNode {
        int value;
        int count;
        int subtree_total;
        int max_freq;
        unsigned int priority;
        int left;
        int right;
    };

    static constexpr int MAX_NODES = 100001;
    static constexpr unsigned int RNG_MUL = 1664525U;
    static constexpr unsigned int RNG_ADD = 1013904223U;
    static constexpr unsigned int RNG_SEED = 42U;

    std::vector<TreapNode> nodes_{TreapNode{}};
    int root_ = 0;
    unsigned int rng_state_ = RNG_SEED;
    std::deque<int> fifo_;
    std::int64_t total_sum_ = 0;
    int total_count_ = 0;

    auto node_at(int index) -> TreapNode& {
        return nodes_[static_cast<std::size_t>(index)];
    }

    auto rng() -> unsigned int {
        rng_state_ = (rng_state_ * RNG_MUL) + RNG_ADD;
        return rng_state_;
    }

    void push_up(int node) {
        auto& current = node_at(node);
        current.subtree_total = current.count;
        current.max_freq = current.count;
        if (current.left != 0) {
            const auto& child = node_at(current.left);
            current.subtree_total += child.subtree_total;
            current.max_freq = std::max(current.max_freq, child.max_freq);
        }
        if (current.right != 0) {
            const auto& child = node_at(current.right);
            current.subtree_total += child.subtree_total;
            current.max_freq = std::max(current.max_freq, child.max_freq);
        }
    }

    auto new_node(int value) -> int {
        nodes_.push_back(TreapNode{.value = value,
                                   .count = 1,
                                   .subtree_total = 1,
                                   .max_freq = 1,
                                   .priority = rng(),
                                   .left = 0,
                                   .right = 0});
        return static_cast<int>(nodes_.size()) - 1;
    }

    auto merge(int left, int right) -> int {
        if (left == 0) {
            return right;
        }
        if (right == 0) {
            return left;
        }
        if (node_at(left).priority > node_at(right).priority) {
            node_at(left).right = merge(node_at(left).right, right);
            push_up(left);
            return left;
        }
        node_at(right).left = merge(left, node_at(right).left);
        push_up(right);
        return right;
    }

    auto treap_add(int node, int value) -> int {
        if (node == 0) {
            return new_node(value);
        }
        if (value == node_at(node).value) {
            ++node_at(node).count;
            push_up(node);
            return node;
        }
        if (value < node_at(node).value) {
            node_at(node).left = treap_add(node_at(node).left, value);
            const int child = node_at(node).left;
            if (node_at(child).priority > node_at(node).priority) {
                node_at(node).left = node_at(child).right;
                node_at(child).right = node;
                push_up(node);
                push_up(child);
                return child;
            }
        } else {
            node_at(node).right = treap_add(node_at(node).right, value);
            const int child = node_at(node).right;
            if (node_at(child).priority > node_at(node).priority) {
                node_at(node).right = node_at(child).left;
                node_at(child).left = node;
                push_up(node);
                push_up(child);
                return child;
            }
        }
        push_up(node);
        return node;
    }

    auto treap_remove(int node, int value) -> int {
        if (node == 0) {
            return 0;
        }
        if (value == node_at(node).value) {
            --node_at(node).count;
            if (node_at(node).count == 0) {
                return merge(node_at(node).left, node_at(node).right);
            }
            push_up(node);
            return node;
        }
        if (value < node_at(node).value) {
            node_at(node).left = treap_remove(node_at(node).left, value);
        } else {
            node_at(node).right = treap_remove(node_at(node).right, value);
        }
        push_up(node);
        return node;
    }

    auto treap_kth(int node, int kth) -> int {
        const int left = node_at(node).left;
        const int left_total = (left != 0) ? node_at(left).subtree_total : 0;
        if (kth < left_total) {
            return treap_kth(left, kth);
        }
        kth -= left_total;
        if (kth < node_at(node).count) {
            return node_at(node).value;
        }
        return treap_kth(node_at(node).right, kth - node_at(node).count);
    }

    auto find_min_with_freq(int node, int target) -> int {
        if (node == 0) {
            return -1;
        }
        const int left = node_at(node).left;
        if (left != 0 && node_at(left).max_freq >= target) {
            const int result = find_min_with_freq(left, target);
            if (result != -1) {
                return result;
            }
        }
        if (node_at(node).count >= target) {
            return node_at(node).value;
        }
        if (node_at(node).right != 0) {
            return find_min_with_freq(node_at(node).right, target);
        }
        return -1;
    }
};

/**
 * Your StatisticsTracker object will be instantiated and called as such:
 * StatisticsTracker* obj = new StatisticsTracker();
 * obj->addNumber(number);
 * obj->removeFirstAddedNumber();
 * int param_3 = obj->getMean();
 * int param_4 = obj->getMedian();
 * int param_5 = obj->getMode();
 */