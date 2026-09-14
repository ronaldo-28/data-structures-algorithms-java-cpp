class AutocompleteSystem {
public:
    AutocompleteSystem(vector<string>& sentences, vector<int>& times) : 
            sentences_(sentences), counts_(sentences.size(), 0) {
        root_ = new TrieNode();
        current_node_ = root_;

        for (int i = 0; i < sentences.size(); i++) {
            insert(i, sentences[i], times[i]);
            sentence_to_index_[sentences[i]] = i;
        }
    }
    
    vector<string> input(char c) {
        if ( c == '#') {
            insert(insert_new_string(current_query_), current_query_, 1);
            current_node_ = root_;
            current_query_.clear();
            return {};
        } else {
            current_query_ += c;
            if (!current_node_ || !current_node_->children[get_index(c)]) {
                current_node_ = nullptr;
                return {};
            }
            else {
                current_node_ = current_node_->children[get_index(c)];
                return form_answer(current_node_);
            }           
        }
    }

    ~AutocompleteSystem() {
        delete root_;
    }

private:

    struct TrieNode {

        TrieNode* children[27] {nullptr};
        int top3[4]; // last element to store the new candiants

        TrieNode() {
            top3[0] = top3[1] = top3[2] = -1;
        }

        ~TrieNode() {
            for ( int i = 0; i < 27; i++) {
                if (children[i]) {
                    delete children[i];
                    children[i] = nullptr;
                }

            }
        }
    };

    TrieNode* root_;
    TrieNode* current_node_;
    string current_query_;

    vector<string> sentences_;
    unordered_map<string, int> sentence_to_index_;
    vector<int> counts_;

    int get_index(char c) {
        return c == ' ' ? 26 : c - 'a';
    }


    void insert(int index, const string& sentence, int count) {
        counts_[index] += count;
        TrieNode* node = root_;
        for (char c : sentence) {
            int idx = get_index(c);
            if (!node->children[idx]) {
                node->children[idx] = new TrieNode();
            }
            node = node->children[idx];
            set_new_index(node, index);
            sort_top3(node);
        }
    }

    void sort_top3(TrieNode* node) {
        auto& top3 = node->top3;
        auto comp = [&](int a, int b) {
            if (a == b) return false;
            
            // Handle -1 cases (empty slots sink to the bottom)
            if (a == -1) return false; // 'a' is lowest, so it does not come before
            if (b == -1) return true;  // 'b' is lowest, so 'a' comes before

            // Primary Sort: Descending by counter
            if (counts_[a] != counts_[b]) {
                return counts_[a] > counts_[b];
            }
            
            // Secondary Sort (Tie-breaker): Ascending by word
            return sentences_[a] < sentences_[b];
        };

        auto sortPair = [&](int& a, int& b) {
            if (comp(b, a)) std::swap(a, b);
        };

        // Optimal 5-step sorting network for exactly 4 elements
        sortPair(top3[0], top3[1]);
        sortPair(top3[2], top3[3]);
        sortPair(top3[0], top3[2]);
        sortPair(top3[1], top3[3]);
        sortPair(top3[1], top3[2]);        
    }

    vector<string> form_answer(TrieNode* node) {
        vector<string> ret;
        auto& top3 = node->top3;
        ret.push_back(sentences_[top3[0]]);
        if (top3[1] != -1) ret.push_back(sentences_[top3[1]]);
        if (top3[2] != -1) ret.push_back(sentences_[top3[2]]);
        return ret;
    }

    int insert_new_string(const string& new_str) {
        auto iter = sentence_to_index_.find(new_str);
        if ( iter == sentence_to_index_.end()) {
            sentence_to_index_[new_str] = sentences_.size();
            sentences_.emplace_back(new_str);
            counts_.push_back(0);
            return sentences_.size() - 1;
        } else {
            return iter->second;
        }
    }

    void set_new_index(TrieNode* node, int index) {
        bool found = false;
        for (int i = 0; i < 3; ++i) {
            if (node->top3[i] == index) {
                found = true;
                break;
            }
        }
            
        if (!found) {
            node->top3[3] = index;
        } else {
            node->top3[3] = -1; // Keep it -1 so it sinks to the bottom during sort
        }
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem* obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj->input(c);
 */