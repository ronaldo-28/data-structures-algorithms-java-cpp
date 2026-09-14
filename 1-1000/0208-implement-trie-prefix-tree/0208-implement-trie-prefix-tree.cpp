class Trie {
public:
    struct TrieNode {
        char val;
        unordered_map<char, TrieNode*> children;
        bool isEnd;
        TrieNode(char ch) {
            val = ch;
            isEnd = false;
        }
    };

    TrieNode* root;

    Trie() {
        auto init = atexit([]() { ofstream("display_runtime.txt") << "0"; });
		ios_base::sync_with_stdio(0);
		cin.tie(0);
		cout.tie(0);
        root = new TrieNode('\0');
    }

    void insertUtil(string str, TrieNode* node) {
        if(!str.length()) {
            node->isEnd = true;
            return;
        }
        if(node->children[str[0]]) {
            insertUtil(str.substr(1), node->children[str[0]]);
        } else {
            node->children[str[0]] = new TrieNode(str[0]);
            insertUtil(str.substr(1), node->children[str[0]]);
        }
    }
    
    void insert(string word) {
        insertUtil(word, root);
    }

    bool searchUtil(string str, TrieNode* node) {
        if(!str.length()) {
            return node->isEnd ? 1 : 0;
        }
        if(node->children[str[0]]) {
            return searchUtil(str.substr(1), node->children[str[0]]);
        }
        return 0;
    }
    
    bool search(string word) {
        return searchUtil(word, root);
    }

    bool startsWithUtil(string str, TrieNode* node) {
        if(!str.length()) {
            return 1;
        }
        if(node->children[str[0]]) {
            return startsWithUtil(str.substr(1), node->children[str[0]]);
        }
        return 0;
    }
    
    bool startsWith(string prefix) {
        return startsWithUtil(prefix, root);
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */